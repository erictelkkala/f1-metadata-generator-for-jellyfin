package erictelkkala.f1_metadata_generator_for_jellyfin.seasons;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.Race;
import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public final class DefaultSeason implements Season {
    private final Date startDate;
    private final Date endDate;
    private final String description;
    private final String posterURL;
    private final Date premierDate;
    private final int seasonNumber;
    private final Date dateAdded;
    private final List<Race> races;

    public DefaultSeason(
            final Date startDate,
            final Date endDate,
            final String description,
            final String posterURL,
            final Date premierDate,
            final int seasonNumber,
            final Date dateAdded,
            final List<Race> races
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.posterURL = posterURL;
        this.description = description;
        this.premierDate = premierDate;
        this.seasonNumber = seasonNumber;
        this.dateAdded = dateAdded;
        this.races = races;
    }

    public DefaultSeason(
            final Date startDate,
            final Date endDate,
            final String posterURL,
            final String description,
            final Date premierDate,
            final int seasonNumber,
            final List<Race> races
    ) {
        this(
                startDate,
                endDate,
                posterURL,
                description,
                premierDate,
                seasonNumber,
                Date.from(Instant.now()),
                races
        );
    }

    @Override
    public List<Race> races() {
        return this.races;
    }

    @Override
    public Document nfo() {
        // TODO: Probably call for-each loop for all races' nfo() methods, then create NFO file if successful
        return new DefaultDocument();
    }
}
