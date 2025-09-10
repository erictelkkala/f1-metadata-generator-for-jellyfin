package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.Race;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public record DefaultWikipediaSource(int season, String raceTitle) implements WikipediaSource {
    private final static Logger LOGGER = LogManager.getLogger(DefaultWikipediaSource.class.getName());

    @Override
    public String seasonDescription() throws SeasonNotFoundException {
        LOGGER.info("Getting season description for season {}", season);
        try (HttpClient client = HttpClient.newHttpClient()) {
            final HttpRequest request = HttpRequest.newBuilder().uri(
                            URI.create("https://en.wikipedia.org/w/api.php?action=query" +
                                    "&prop=extracts" +
                                    "&exintro" +
                                    "&exlimit=1" +
                                    "&exsectionformat=plain" +
                                    "&format=json" +
                                    "&titles=" +
                                    this.season +
                                    "%20Formula%20One%20World%20Championship" +
                                    "&explaintext=1"
                            )
                    )
                    .header("User-Agent", "F1MetadataGenerator/1.0 (https://github.com/erictelkkala/f1-metadata-generator-for-jellyfin) Java-http-client")
                    .GET().build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new SeasonNotFoundException("Season not found: " + this.season);
            } else {
                LOGGER.debug("Found information for season {}", season);

                final JsonObject body = Json.createReader(new StringReader(response.body())).readObject();
                final JsonObject pages = body.getJsonObject("query").getJsonObject("pages");
                final String pageID = pages.keySet().iterator().next();
                LOGGER.debug("Found page {}", pageID);

                return pages.getJsonObject(pageID).getString("extract");
            }
        } catch (IOException | InterruptedException exception) {
            LOGGER.error(exception);
            throw new SeasonNotFoundException("Season not found: " + this.season);
        }
    }

    @Override
    public Race race() throws RaceNotFoundException {
        LOGGER.debug("Getting data for race {}", raceTitle);
        return null;
    }
}
