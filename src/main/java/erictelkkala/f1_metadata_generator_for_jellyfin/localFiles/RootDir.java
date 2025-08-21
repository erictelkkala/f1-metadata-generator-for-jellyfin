package erictelkkala.f1_metadata_generator_for_jellyfin.localFiles;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public record RootDir(File dir) {

    public MediaFiles mediaFiles() {
        final File[] files = dir.listFiles();
        if (files == null || files.length == 0) return new MediaFiles(Collections.emptySet());

        final Set<MediaFile> mediaFiles = new HashSet<>();

        for (File file : files) {
            if (file.isFile() && (file.getName().toLowerCase(Locale.ROOT).endsWith(".mkv") || file.getName().toLowerCase(Locale.ROOT).endsWith(".mp4"))) {
                mediaFiles.add(new MediaFile(file));
            }
        }

        return new MediaFiles(mediaFiles);
    }
}
