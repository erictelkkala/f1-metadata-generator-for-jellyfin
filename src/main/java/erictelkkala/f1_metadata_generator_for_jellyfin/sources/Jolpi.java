package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.DefaultWeekend;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public final class Jolpi {
    private final static Logger LOGGER = LogManager.getLogger(Jolpi.class);
    private final static String RACES_URI = "/races/";
    public final URI uri;

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

    private DefaultWeekend parseWeekend(final JsonObject jsonObject) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        final int raceNumber = jsonObject.getInt("round");
        final String raceName = jsonObject.getString("raceName");
        final int circuitID = jsonObject.getJsonObject("Circuit").getInt("circuitId");
        final String raceDate = jsonObject.getString("date");
        final String raceTime = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        final String qualiDate = this.parseDate(jsonObject, "Qualifying");
        final String qualiTime = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        final String sprintDate = parseDate(jsonObject, "Qualifying");
        final String sprintTime = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        final String sprintQualiDate = this.parseDate(jsonObject, "SprintQualifying");
        final String sprintQualiTime = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";

        final String FP1Date = this.parseDate(jsonObject, "FirstPractice");
        final String FP1Time = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        final String FP2Date = this.parseDate(jsonObject, "ThirdPractice");
        final String FP2Time = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        final String FP3Date = this.parseDate(jsonObject, "ThirdPractice");
        final String FP3Time = jsonObject.containsKey("time") ? jsonObject.getString("time") : "00:00:00Z";
        return new DefaultWeekend(
                raceNumber,
                LocalDateTime.parse(raceDate + "T" + raceTime, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                raceName,
                circuitID,
                sprintDate.equals("1970-01-01") ? null : LocalDateTime.parse(sprintDate + "T" + sprintTime, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                FP1Date.equals("1970-01-01") ? null : LocalDateTime.parse(FP1Date + "T" + FP1Time, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                FP2Date.equals("1970-01-01") ? null : LocalDateTime.parse(FP2Date + "T" + FP2Time, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                FP3Date.equals("1970-01-01") ? null : LocalDateTime.parse(FP3Date + "T" + FP3Time, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                qualiDate.equals("1970-01-01") ? null : LocalDateTime.parse(qualiDate + "T" + qualiTime, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC),
                sprintQualiDate.equals("1970-01-01") ? null : LocalDateTime.parse(sprintQualiDate + "T" + sprintQualiTime, dateTimeFormatter).toInstant(java.time.ZoneOffset.UTC)
        );
    }

    private String parseDate(final JsonObject jsonObject, final String key) {
        if (jsonObject.containsKey(key)) {
            return jsonObject.getJsonObject(key).getString("date");
        } else {
            return "1970-01-01";
        }
    }

}
