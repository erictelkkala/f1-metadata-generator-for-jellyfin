package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;

import java.io.IOException;
import java.time.Instant;

public class DefaultWeekend implements Race {
    private final int raceNumber;
    private final Instant raceDate;
    private final String raceName;
    private final int circuitID;
    private final Instant sprintDate;
    private final Instant fp1Date;
    private final Instant fp2Date;
    private final Instant fp3Date;
    private final Instant qualiDate;
    private final Instant sprintQualiDate;

    public DefaultWeekend(
            final int raceNumber,
            final Instant raceDate,
            final String raceName,
            final int circuitID,
            final Instant sprintDate,
            final Instant fp1Date,
            final Instant fp2Date,
            final Instant fp3Date,
            final Instant qualiDate,
            final Instant sprintQualiDate
    ) {
        this.raceNumber = raceNumber;
        this.raceDate = raceDate;
        this.raceName = raceName;
        this.circuitID = circuitID;
        this.sprintDate = sprintDate;
        this.fp1Date = fp1Date;
        this.fp2Date = fp2Date;
        this.fp3Date = fp3Date;
        this.qualiDate = qualiDate;
        this.sprintQualiDate = sprintQualiDate;
    }

    @Override
    public Document nfo() throws IOException {
        return new DefaultDocument();
    }
}
