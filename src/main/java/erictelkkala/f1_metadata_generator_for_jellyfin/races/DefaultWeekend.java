package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;

import java.io.IOException;
import java.util.Date;

public class DefaultWeekend implements Race {
    private final int raceNumber;
    private final Date raceDate;
    private final String raceName;
    private final int circuitID;
    private final Date sprintDate;
    private final Date fp1Date;
    private final Date fp2Date;
    private final Date fp3Date;
    private final Date qualiDate;
    private final Date sprintQualiDate;

    public DefaultWeekend(
            final int raceNumber,
            final Date raceDate,
            final String raceName,
            final int circuitID,
            final Date sprintDate,
            final Date fp1Date,
            final Date fp2Date,
            final Date fp3Date,
            final Date qualiDate,
            final Date sprintQualiDate
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
