package erictelkkala.f1_metadata_generator_for_jellyfin.altNames;

import java.util.Map;

public record ValidatedAlternativeNames(AlternativeNames alternativeNames) implements AlternativeNames {

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
