package erictelkkala.f1_metadata_generator_for_jellyfin.seasons;

import java.io.IOException;
import java.util.List;

public final class AllSeasons {
    private final List<Season> seasons;

    public AllSeasons(final List<Season> seasons) {
        this.seasons = seasons;
    }


    public void nfo() throws IOException {
        for (final Season season : seasons) {
            // TODO: file structure?
            season.nfo();
        }
    }
}
