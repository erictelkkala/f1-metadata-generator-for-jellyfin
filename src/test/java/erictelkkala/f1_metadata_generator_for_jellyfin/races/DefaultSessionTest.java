package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import erictelkkala.f1_metadata_generator_for_jellyfin.PrettyNfo;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class DefaultSessionTest {
    @Test
    void nfoIsCreated(@TempDir Path tempDir) throws IOException, ParseException, DocumentException {
        final File posterFile = tempDir.resolve("poster.png").toFile();
        Document expectedDocument = DocumentHelper.createDocument();
        Element details = expectedDocument.addElement("episodedetails");
        details.addElement("lockdata").addText("true");
        details.addElement("title").addText("title");
        details.addElement("sorttitle").addText("title3");
        details.addElement("season").addText("2025");
        details.addElement("episode").addText("1");
        details.addElement("plot").addText("plot");
        details.addElement("aired").addText("2025-01-01");
        details.addElement("dateadded").addText("2025-01-01");
        details.addElement("year").addText("2025");

        Element artElement = details.addElement("art");
        artElement.addElement("poster").addText(posterFile.getCanonicalPath());

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
                posterFile,
                "yyyy-MM-dd"
        );

        final Document returnedDocument = session.nfo();

        // Output matches a human-readable format
        Assertions.assertEquals(new PrettyNfo(expectedDocument).document().asXML(), new PrettyNfo(returnedDocument).document().asXML());
    }

    @Test
    @DisplayName("equalsVerifier")
    void equalsVerifier() {
        EqualsVerifier.forClass(DefaultSession.class).verify();
    }
}