package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultWikipediaSourceTest {
    @Test
    @DisplayName("seasonDescription returns correct information")
    void seasonDescriptionReturnsCorrectInformation() {
        final DefaultWikipediaSource defaultWikipediaSource = new DefaultWikipediaSource(2025, "raceTitle");

        final var response = defaultWikipediaSource.seasonDescription();

        Assertions.assertTrue(response.contains("2025 FIA Formula One World Championship"));
    }
}