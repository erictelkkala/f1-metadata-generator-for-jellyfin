package org.example.races;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.Date;

public final class DefaultSession implements Session {
    private final SessionType sessionType;
    private final String title;
    private final String sortTitle;
    private final int season;
    private final int episode;
    private final String plot;
    private final Date date;
    private final Date dateAdded;
    private final int year;
    private final String posterPath;

    public DefaultSession(
            final SessionType sessionType,
            final String title,
            final String sortTitle,
            final int season,
            final int episode,
            final String plot,
            final Date date,
            final Date dateAdded,
            final int year,
            final String posterPath
    ) {
        this.sessionType = sessionType;
        this.title = title;
        this.sortTitle = sortTitle;
        this.season = season;
        this.episode = episode;
        this.plot = plot;
        this.date = date;
        this.dateAdded = dateAdded;
        this.year = year;
        this.posterPath = posterPath;
    }

    @Override
    public SessionType sessionType() {
        return null;
    }

    @Override
    public void nfo() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("episodedetails")
                .addAttribute("lockdata", "true")
                .addAttribute("title", this.title)
                .addAttribute("sorttitle", this.sortTitle)
                .addAttribute("season", String.valueOf(this.season))
                .addAttribute("episode", String.valueOf(this.episode))
                .addAttribute("plot", this.plot)
                .addAttribute("aired", this.date)
                .addAttribute("dateadded", this.dateAdded)
                .addAttribute("year", this.year)
                .element("art")
                .addAttribute("poster", this.posterPath);
    }
}
