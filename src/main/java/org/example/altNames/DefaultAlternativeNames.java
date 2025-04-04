package org.example.altNames;

import java.util.HashMap;
import java.util.Map;

public final class DefaultAlternativeNames implements AlternativeNames {
    private final Map<String, String> names;

    public DefaultAlternativeNames(Map<String, String> names) {
        this.names = names;
    }

    public DefaultAlternativeNames() {
        this(
                new HashMap<>()
        );
    }

    public String alternativeName(final String name) {
        return this.names.get(name);
    }

    @Override
    public Map<String, String> allAlternativeNames() {
        return this.names;
    }
}
