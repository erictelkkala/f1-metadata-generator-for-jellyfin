package org.example.races;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class DefaultSessionTest {
    @Test
    void nfoIsCreated() throws IOException, ParseException {
        Document expectedDocument = DocumentHelper.createDocument();
        Element details = expectedDocument.addElement("episodedetails");
        Element lockData = details.addElement("lockdata").addText("true");
        Element title = details.addElement("title").addText("title");
        Element sorttitle = details.addElement("sorttitle").addText("title3");
        Element season = details.addElement("season").addText("2025");
        Element episode = details.addElement("episode").addText("1");
        Element plot = details.addElement("plot").addText("plot");
        Element aired = details.addElement("aired").addText("2025-01-01");
        Element dateadded = details.addElement("dateadded").addText("2025-01-01");
        Element year = details.addElement("year").addText("2025");

        Element artElement = details.addElement("art");
        artElement.addElement("poster").addText("poster");

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DefaultSession session = new DefaultSession(
                SessionType.RACE,
                "title",
                "title3",
                2025,
                1,
                "plot",
                dateFormat.parse("2025-01-01"),
                dateFormat.parse("2025-01-01"),
                2025,
                "poster",
                "yyyy-MM-dd"
        );

        final Document returnedDocument = session.nfo();

        Assertions.assertEquals(expectedDocument.asXML(), returnedDocument.asXML());
    }
}