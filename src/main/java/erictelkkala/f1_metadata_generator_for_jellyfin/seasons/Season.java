package erictelkkala.f1_metadata_generator_for_jellyfin.seasons;

import erictelkkala.f1_metadata_generator_for_jellyfin.Nfo;
import erictelkkala.f1_metadata_generator_for_jellyfin.races.Race;

import java.util.List;

public interface Season extends Nfo {
    List<Race> races();
}
