package erictelkkala.f1_metadata_generator_for_jellyfin.altNames;

import java.util.Map;

public interface AlternativeNames {
    String alternativeName(final String name);

    Map<String, String> allAlternativeNames();
}
