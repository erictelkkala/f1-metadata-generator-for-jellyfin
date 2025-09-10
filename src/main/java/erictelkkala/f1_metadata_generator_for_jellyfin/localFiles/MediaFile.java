package erictelkkala.f1_metadata_generator_for_jellyfin.localFiles;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record MediaFile(File file) {

    public boolean hasNfo() {
        final String fileName = file.getName().substring(0, file.getName().indexOf("."));
        final File parentDir = this.file.getParentFile();
        final File[] files = parentDir.listFiles();
        if (files != null && files.length != 0) {
            return Stream.of(files).filter(File::isFile).anyMatch(file1 -> file1.getName().equals(fileName + ".nfo"));
        } else {
            return false;
        }
    }

    public String episodeNumber() {
        final String fileName = file.getName();
        final Pattern pattern = Pattern.compile("\\d{2} - ");
        final Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            final int indexAfter = matcher.end();
            return fileName.substring(indexAfter - 5, indexAfter - 3);

        } else {
            throw new IllegalArgumentException("No episode number found in file name: " + fileName);
        }
    }
}
