package org.example.altNames;

import java.util.Map;

public final class ValidatedAlternativeNames implements AlternativeNames {
    private final AlternativeNames alternativeNames;

    public ValidatedAlternativeNames(AlternativeNames alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    @Override
    public String alternativeName(String name) {
        if (!this.contains(name)) {
            throw new IllegalArgumentException("Name " + name + " was not found");
        }
        return this.alternativeNames.alternativeName(name);
    }

    @Override
    public Map<String, String> allAlternativeNames() {
        return this.alternativeNames.allAlternativeNames();
    }

    private boolean contains(final String name) {
        return this.alternativeNames.allAlternativeNames().containsKey(name);
    }
}
