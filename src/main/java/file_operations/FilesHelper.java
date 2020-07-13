package file_operations;

import lombok.NonNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public final class FilesHelper {

    private static final Charset DEFAULT_CHARSET = UTF_8;

    private FilesHelper() {
        throw new UnsupportedOperationException();
    }

    public static boolean exists(@NonNull final Path path) {
        return (Files.exists(path, NOFOLLOW_LINKS));
    }

    public static long getFileSize(@NonNull final Path path) throws IOException {
        return (Files.size(path));
    }

    public static boolean isEmpty(@NonNull final Path path) throws IOException {
        final long fileSize = getFileSize(path);
        return (fileSize == 0);
    }

    public static Boolean getEmptyStatus(@NonNull final Path path) throws IOException {
        if (!exists(path)) {
            return (null);
        }
        return (isEmpty(path));
    }

    public static void create(@NonNull final Path path) throws IOException {
        Files.createFile(path);
    }

    public static boolean createIfNotExists(@NonNull final Path path) throws IOException {
        if (!exists(path)) {
            create(path);
            return (true);
        }
        return (false);
    }

    public static void delete(@NonNull final Path path) throws IOException {
        Files.delete(path);
    }

    public static boolean deleteIfExists(@NonNull final Path path) throws IOException {
        if (exists(path)) {
            delete(path);
            return (true);
        }
        return (false);
    }

    public static void clear(@NonNull final Path path) throws IOException {
        Files.newBufferedWriter(path, DEFAULT_CHARSET, TRUNCATE_EXISTING);
    }

    public static boolean clearIfNotEmpty(@NonNull final Path path) throws IOException {
        if (!isEmpty(path)) {
            clear(path);
            return (true);
        }
        return (false);
    }

    public static boolean createOrClearIfExists(@NonNull final Path path) throws IOException {
        if (!exists(path)) {
            create(path);
            return (true);
        }
        if (!isEmpty(path)) {
            clear(path);
            return (true);
        }
        return (false);
    }

}
