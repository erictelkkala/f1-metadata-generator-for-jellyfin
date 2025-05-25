package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

public class SeasonNotFoundException extends RuntimeException {
    public SeasonNotFoundException(String message) {
        super(message);
    }
}
