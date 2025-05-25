package erictelkkala.f1_metadata_generator_for_jellyfin;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;

public final class PrettyNfo {
    private final Document document;

    public PrettyNfo(Document document) {
        this.document = document;
    }


    public Document document() throws IOException {
        final OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        final StringWriter sw = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(sw, outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();

        try {
            return DocumentHelper.parseText(sw.toString());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
