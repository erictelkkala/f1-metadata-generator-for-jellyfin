package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;

import java.io.IOException;
import java.time.Instant;

public record DefaultWeekend(int raceNumber, Instant raceDate, String raceName, String circuitID, Instant sprintDate,
                             Instant fp1Date, Instant fp2Date, Instant fp3Date, Instant qualiDate,
                             Instant sprintQualiDate) implements Race {

    @Override
    public Document nfo() throws IOException {
        return new DefaultDocument();
    }
}
