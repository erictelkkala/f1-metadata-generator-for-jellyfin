package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import erictelkkala.f1_metadata_generator_for_jellyfin.races.Race;

public interface WikipediaSource {
    String seasonDescription();

    Race race();
}
