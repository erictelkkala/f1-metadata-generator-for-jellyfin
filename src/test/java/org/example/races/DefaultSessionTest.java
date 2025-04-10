package org.example.races;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSessionTest {
    @Test
    void nfoIsCreated() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("episodedetails")
                .addAttribute("lockdata", "true")
                .addAttribute("title", "title")
                .addAttribute("sorttitle", "title3")
                .addAttribute("season", "2025")
                .addAttribute("episode" , "1")
                .addAttribute("plot", "plot")
                .addAttribute("aired", "2025-01-01T00:00:00Z")
                .addAttribute("dateadded", "2025-01-01")
                .addAttribute("year", "2025")
                .element("art")
                .addAttribute("poster", "poster");

    }
}