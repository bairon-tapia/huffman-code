package file_operations;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.NonNull;

public final class BinaryFilesHelper {

    private BinaryFilesHelper() {
        throw new UnsupportedOperationException();
    }

    public static void writeBytes(@NonNull final Path path, @NonNull byte[] content) throws IOException {
        Files.write(path, content, NOFOLLOW_LINKS, TRUNCATE_EXISTING);
    }

    public static boolean writeIfNotExistsOrIsEmpty(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        if (!FilesHelper.exists(path)) {
            FilesHelper.create(path);
            writeBytes(path, content);
            return (true);
        }
        if (FilesHelper.isEmpty(path)) {
            writeBytes(path, content);
            return (true);
        }
        return (false);
    }

    public static byte[] readBytes(@NonNull final Path path) throws IOException {
        if (!FilesHelper.exists(path)) {
            return (null);
        }
        return (Files.readAllBytes(path));
    }

}
