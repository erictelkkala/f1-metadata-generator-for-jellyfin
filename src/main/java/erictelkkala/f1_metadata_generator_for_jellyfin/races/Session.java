package erictelkkala.f1_metadata_generator_for_jellyfin.races;

import erictelkkala.f1_metadata_generator_for_jellyfin.Nfo;

public interface Session extends Nfo {
    SessionType sessionType();
}
