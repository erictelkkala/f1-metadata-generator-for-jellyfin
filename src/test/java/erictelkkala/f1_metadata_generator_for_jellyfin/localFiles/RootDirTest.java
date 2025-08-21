package erictelkkala.f1_metadata_generator_for_jellyfin.localFiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;

final class RootDirTest {

    @Test
    @DisplayName("mediaFiles() lists both MP4 and MKV files if found")
    void mediaFilesListsBothMp4AndMkvFilesIfFound(@TempDir File tempDir) {
        Assertions.assertDoesNotThrow(() -> Files.createFile(tempDir.toPath().resolve("1stFile.mp4")));
        Assertions.assertDoesNotThrow(() -> Files.createFile(tempDir.toPath().resolve("2ndFile.mkv")));
        Assertions.assertDoesNotThrow(() -> Files.createFile(tempDir.toPath().resolve("3rdFile.png")));

        final RootDir rootDir = new RootDir(tempDir);

        final MediaFiles mediaFiles = rootDir.mediaFiles();

        Assertions.assertEquals(2, mediaFiles.mediaFiles().size());
    }
}