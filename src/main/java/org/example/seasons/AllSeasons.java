package org.example.seasons;

import org.example.Nfo;

import java.io.IOException;
import java.util.List;

public final class AllSeasons implements Nfo {
    private final List<Season> seasons;

    public AllSeasons(final List<Season> seasons) {
        this.seasons = seasons;
    }


    @Override
    public void nfo() throws IOException {
        for (final Season season : seasons) {
            // TODO: file structure?
            season.nfo();
        }
    }
}
