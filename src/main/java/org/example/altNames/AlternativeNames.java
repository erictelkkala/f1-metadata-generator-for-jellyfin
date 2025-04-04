package org.example.altNames;

import java.util.Map;

public interface AlternativeNames {
    String alternativeName(final String name);

    Map<String, String> allAlternativeNames();
}
