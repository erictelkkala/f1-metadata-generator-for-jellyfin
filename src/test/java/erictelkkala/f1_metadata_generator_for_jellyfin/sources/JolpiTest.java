package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.DefaultWeekend;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;

class JolpiTest {
    @Test
    @DisplayName("parseWeekend returns correct DefaultWeekend if JsonObject contains all the correct data")
    void parseWeekendReturnsCorrectDefaultWeekendIfJsonObjectContainsAllTheCorrectData() {
        final File file = new File("src/test/resources/exampleRaceJolpiResponse.json");
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(file.canRead());
        final FileInputStream fileInputStream = Assertions.assertDoesNotThrow(() -> new FileInputStream(file));
        Assertions.assertNotNull(fileInputStream);
        final Jolpi jolpi = new Jolpi();

        final JsonObject jsonObject = Json.createReader(new BufferedReader(new java.io.InputStreamReader(fileInputStream))).readObject();

        final DefaultWeekend expectedDefaultWeekend = new DefaultWeekend(
                2,
                Instant.parse("2025-03-23T07:00:00Z"),
                "Chinese Grand Prix",
                "shanghai",
                Instant.parse("2025-03-22T03:00:00Z"),
                Instant.parse("2025-03-21T03:30:00Z"),
                Instant.parse("1970-01-01T00:00:00Z"),
                Instant.parse("1970-01-01T00:00:00Z"),
                Instant.parse("2025-03-22T07:00:00Z"),
                Instant.parse("2025-03-21T07:30:00Z")
        );

        final DefaultWeekend returnedDefaultWeekend = jolpi.parseWeekend(jsonObject);

        Assertions.assertEquals(expectedDefaultWeekend, returnedDefaultWeekend);
    }
}