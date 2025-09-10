package exampleXML;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.nio.file.Path;

public final class ExampleXMLFile {
    private final Path path;

    public ExampleXMLFile(Path path) {
        this.path = path;
    }

    public ExampleXMLFile() {
        this(Path.of("src/test/resources/exampleEpisodeFile.nfo"));
    }

    public Document document() throws DocumentException {
        final SAXReader reader = new SAXReader();
        return reader.read(this.path.toFile());
    }
}
