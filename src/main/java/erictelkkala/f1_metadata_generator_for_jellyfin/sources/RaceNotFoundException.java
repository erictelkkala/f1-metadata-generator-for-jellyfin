package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(String message) {
        super(message);
    }
}
