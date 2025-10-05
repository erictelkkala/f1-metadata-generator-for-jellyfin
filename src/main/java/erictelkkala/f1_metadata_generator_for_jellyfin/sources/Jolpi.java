package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.DefaultWeekend;
import erictelkkala.f1_metadata_generator_for_jellyfin.sources.jolpi.JsonSession;
import erictelkkala.f1_metadata_generator_for_jellyfin.sources.jolpi.JsonSessionImpl;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public final class Jolpi {
    private final static Logger LOGGER = LogManager.getLogger(Jolpi.class);
    private final static String RACES_URI = "/races/";
    private final URI uri;

    public Jolpi(URI uri) {
        this.uri = uri;
    }

    public Jolpi() {
        this(
                URI.create("https://api.jolpi.ca/ergast/f1/")
        );
    }

    public Set<DefaultWeekend> weekends(final int season) {
        final Set<DefaultWeekend> weekends = new HashSet<>();

        final URI offsetURI = URI.create(uri + String.valueOf(season) + RACES_URI);
        try (HttpClient client = HttpClient.newHttpClient()) {
            final HttpRequest request = HttpRequest.newBuilder().uri(offsetURI).build();
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new SeasonNotFoundException("Season not found: ");
            } else {
                LOGGER.debug("Found information for season {}", season);

                final JsonObject body = Json.createReader(new StringReader(response.body())).readObject();
                final JsonArray jsonValues = body.getJsonObject("MRData").getJsonObject("RaceTable").getJsonArray("Races");
                for (final JsonValue jsonValue : jsonValues) {
                    final DefaultWeekend weekend = this.parseWeekend(jsonValue.asJsonObject());
                    weekends.add(weekend);
                }
            }
        } catch (IOException | InterruptedException exception) {
            LOGGER.error(exception);
            throw new SeasonNotFoundException("Season not found: " + season);
        }

        return weekends;
    }

    public DefaultWeekend parseWeekend(final JsonObject jsonObject) {
        final int raceNumber = Integer.parseInt(jsonObject.getString("round"));
        final String raceName = jsonObject.getString("raceName");
        final String circuitID = jsonObject.getJsonObject("Circuit").getString("circuitId");
        final JsonSession fp1Session = new JsonSessionImpl(jsonObject.getJsonObject("FirstPractice"));
        final JsonSession fp2Session = new JsonSessionImpl(jsonObject.getJsonObject("SecondPractice"));
        final JsonSession fp3Session = new JsonSessionImpl(jsonObject.getJsonObject("ThirdPractice"));
        final JsonSession qualiSession = new JsonSessionImpl(jsonObject.getJsonObject("Qualifying"));
        final JsonSession raceSession = new JsonSessionImpl(jsonObject);
        final JsonSession sprintQualiSession = new JsonSessionImpl(jsonObject.getJsonObject("SprintQualifying"));
        final JsonSession sprintSession = new JsonSessionImpl(jsonObject.getJsonObject("Sprint"));


        return new DefaultWeekend(
                raceNumber,
                new DateTime(raceSession.date(), raceSession.time()).asInstant(),
                raceName,
                circuitID,
                new DateTime(sprintSession.date(), sprintSession.time()).asInstant(),
                new DateTime(fp1Session.date(), fp1Session.time()).asInstant(),
                new DateTime(fp2Session.date(), fp2Session.time()).asInstant(),
                new DateTime(fp3Session.date(), fp3Session.time()).asInstant(),
                new DateTime(qualiSession.date(), qualiSession.time()).asInstant(),
                new DateTime(sprintQualiSession.date(), sprintQualiSession.time()).asInstant()
        );
    }
}
