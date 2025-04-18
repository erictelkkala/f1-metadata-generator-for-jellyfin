package org.example.races;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
    private final String timeFormat;

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
            final String posterPath,
            final String timeFormat
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
        this.timeFormat = timeFormat;
    }

    @Override
    public SessionType sessionType() {
        return null;
    }

    @Override
    public Document nfo() throws IOException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(this.timeFormat);

        Document document = DocumentHelper.createDocument();
        Element details = document.addElement("episodedetails");
        Element lockData = details.addElement("lockdata").addText("true");
        Element title = details.addElement("title").addText(this.title);
        Element sorttitle = details.addElement("sorttitle").addText(this.sortTitle);
        Element season = details.addElement("season").addText(String.valueOf(this.season));
        Element episode = details.addElement("episode").addText(String.valueOf(this.episode));
        Element plot = details.addElement("plot").addText(this.plot);
        Element aired = details.addElement("aired").addText(dateFormat.format(this.date));
        Element dateadded = details.addElement("dateadded").addText(dateFormat.format(this.dateAdded));
        Element year = details.addElement("year").addText(String.valueOf(this.year));

        Element artElement = details.addElement("art");
        artElement.addElement("poster").addText(this.posterPath);

        return document;
    }
}
