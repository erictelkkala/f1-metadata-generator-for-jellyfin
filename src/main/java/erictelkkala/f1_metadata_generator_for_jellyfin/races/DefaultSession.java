package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    private final File posterPath;
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
            final File posterPath,
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
        return this.sessionType;
    }

    @Override
    public Document nfo() throws IOException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(this.timeFormat);

        Document document = DocumentHelper.createDocument();
        Element details = document.addElement("episodedetails");
        details.addElement("lockdata").addText("true");
        details.addElement("title").addText(this.title);
        details.addElement("sorttitle").addText(this.sortTitle);
        details.addElement("season").addText(String.valueOf(this.season));
        details.addElement("episode").addText(String.valueOf(this.episode));
        details.addElement("plot").addText(this.plot);
        details.addElement("aired").addText(dateFormat.format(this.date));
        details.addElement("dateadded").addText(dateFormat.format(this.dateAdded));
        details.addElement("year").addText(String.valueOf(this.year));

        Element artElement = details.addElement("art");
        artElement.addElement("poster").addText(this.posterPath.getCanonicalPath());

        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DefaultSession that = (DefaultSession) o;
        return season == that.season && episode == that.episode && year == that.year && sessionType == that.sessionType && Objects.equals(title, that.title) && Objects.equals(sortTitle, that.sortTitle) && Objects.equals(plot, that.plot) && Objects.equals(date, that.date) && Objects.equals(dateAdded, that.dateAdded) && Objects.equals(posterPath, that.posterPath) && Objects.equals(timeFormat, that.timeFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionType, title, sortTitle, season, episode, plot, date, dateAdded, year, posterPath, timeFormat);
    }
}
