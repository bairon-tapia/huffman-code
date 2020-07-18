package file_operations;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.NonNull;

public class TextFileHelper {

    private static final Charset DEFAULT_CHARSET = UTF_8;

    private TextFileHelper() {
        throw new UnsupportedOperationException();
    }

    public static void writeString(@NonNull final Path path, @NonNull final String content) throws IOException {
        Files.writeString(path, content, NOFOLLOW_LINKS);
    }

    public static void writeAppendMode(@NonNull final Path path, @NonNull final String content) throws IOException {
        Files.writeString(path, content, NOFOLLOW_LINKS, APPEND);
    }

    public static void writeTruncateMode(@NonNull final Path path, @NonNull final String content) throws IOException {
        Files.writeString(path, content, NOFOLLOW_LINKS, TRUNCATE_EXISTING);
    }

    public static boolean writeIfNotExists(@NonNull final Path path, @NonNull final String content) throws IOException {
        if (!FilesHelper.exists(path)) {
            FilesHelper.create(path);
            writeAppendMode(path, content);
            return (true);
        }
        return (false);
    }

    public static boolean writeIfIsEmpty(@NonNull final Path path, @NonNull final String content) throws IOException {
        if (FilesHelper.exists(path) && FilesHelper.isEmpty(path)) {
            writeAppendMode(path, content);
            return (true);
        }
        return (false);
    }

    public static boolean writeIfNotExistsOrIsEmpty(@NonNull final Path path, @NonNull final String content) throws IOException {
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

    public static String readString(@NonNull final Path path) throws IOException {
        if (!FilesHelper.exists(path)) {
            return (null);
        }
        return (Files.readString(path, DEFAULT_CHARSET));
    }
}
