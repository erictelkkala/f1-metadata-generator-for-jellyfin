package erictelkkala.f1_metadata_generator_for_jellyfin;

import org.dom4j.Document;

import java.io.IOException;


public interface Nfo {
    Document nfo() throws IOException;
}
