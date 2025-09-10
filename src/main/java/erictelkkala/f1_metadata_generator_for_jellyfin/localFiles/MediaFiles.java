package erictelkkala.f1_metadata_generator_for_jellyfin.localFiles;

import java.util.Set;

public record MediaFiles(Set<MediaFile> mediaFiles) {

    public void parse() {
        for (final MediaFile mediaFile : mediaFiles) {
            if (!mediaFile.hasNfo()) {

            }
        }
    }
}
