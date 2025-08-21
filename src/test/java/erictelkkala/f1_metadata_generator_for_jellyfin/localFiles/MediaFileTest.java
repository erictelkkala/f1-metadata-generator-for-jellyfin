package erictelkkala.f1_metadata_generator_for_jellyfin.localFiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;


final class MediaFileTest {
    @Test
    @DisplayName("hasNfo returns true if there is an NFO file with the same name")
    void hasNfoReturnsTrueIfThereIsAnNfoFileWithTheSameName(@TempDir Path tempDir) {
        final Path mediaFile = Assertions.assertDoesNotThrow(() -> Files.createFile(Path.of(tempDir + "/mediaFile.mkv")));
        Assertions.assertDoesNotThrow(() -> Files.createFile(Path.of(tempDir + "/mediaFile.nfo")));


        Assertions.assertNotNull(mediaFile);
        final MediaFile mediaFile1 = new MediaFile(mediaFile.toFile());

        Assertions.assertTrue(mediaFile1.hasNfo());
    }

    @Test
    @DisplayName("hasNfo returns false if there is not an NFO file with the same name")
    void hasNfoReturnsFalseIfThereIsNotAnNfoFileWithTheSameName(@TempDir Path tempDir) {
        final Path mediaFile = Assertions.assertDoesNotThrow(() -> Files.createFile(Path.of(tempDir + "/mediaFile.mkv")));
        Assertions.assertDoesNotThrow(() -> Files.createFile(Path.of(tempDir + "/mediaFileThisIsNot.nfo")));


        Assertions.assertNotNull(mediaFile);
        final MediaFile mediaFile1 = new MediaFile(mediaFile.toFile());

        Assertions.assertFalse(mediaFile1.hasNfo());
    }
}