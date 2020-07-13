package file_operations;

import lombok.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public final class BinaryFilesHelper {

    private BinaryFilesHelper() {
        throw new UnsupportedOperationException();
    }

    public static void writeBytes(@NonNull final Path path, @NonNull byte[] content,
                                  @NonNull final StandardOpenOption standardOpenOption) throws IOException {
        Files.write(path, content, NOFOLLOW_LINKS, standardOpenOption);
    }

    public static void writeAppendMode(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        writeBytes(path, content, APPEND);
    }

    public static void writeTruncateMode(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        writeBytes(path, content, TRUNCATE_EXISTING);
    }

    public static boolean writeIfNotExists(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        if (!FilesHelper.exists(path)) {
            FilesHelper.create(path);
            writeAppendMode(path, content);
            return (true);
        }
        return (false);
    }

    public static boolean writeIfIsEmpty(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        if (FilesHelper.exists(path) && FilesHelper.isEmpty(path)) {
            writeAppendMode(path, content);
            return (true);
        }
        return (false);
    }

    public static boolean writeIfNotExistsOrIsEmpty(@NonNull final Path path, @NonNull final byte[] content) throws IOException {
        if (!FilesHelper.exists(path)) {
            FilesHelper.create(path);
            writeAppendMode(path, content);
            return (true);
        }
        if (FilesHelper.isEmpty(path)) {
            writeAppendMode(path, content);
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
