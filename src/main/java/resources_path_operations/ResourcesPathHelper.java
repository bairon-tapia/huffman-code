package resources_path_operations;

import lombok.NonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ResourcesPathHelper {

    private static final String SEPARATOR;
    private static final String DIRECTORY_FOLDER;
    private static final String FILE_FOLDER_RELATIVE_PATH;
    private static final String FILE_FOLDER_ABSOLUTE_PATH;

    static {
        SEPARATOR = System.getProperty("file.separator");
        DIRECTORY_FOLDER = System.getProperty("user.dir");
        FILE_FOLDER_RELATIVE_PATH = String.format(
                "%s%s%s%s%s",
                "src",
                SEPARATOR, "main",
                SEPARATOR, "resources");
        FILE_FOLDER_ABSOLUTE_PATH = String.format(
                "%s%s%s",
                DIRECTORY_FOLDER,
                SEPARATOR, FILE_FOLDER_RELATIVE_PATH);
    }

    private ResourcesPathHelper() {
        throw new UnsupportedOperationException();
    }

    public static String getRelativePathAsString(@NonNull final String fileName) {
        return (FILE_FOLDER_RELATIVE_PATH + SEPARATOR + fileName);
    }

    public static String getAbsolutePathAsString(@NonNull final String fileName) {
        return (FILE_FOLDER_ABSOLUTE_PATH + SEPARATOR + fileName);
    }

    public static Path getRelativePath(@NonNull final String fileName) {
        final String relativePath = getRelativePathAsString(fileName);
        return (Paths.get(relativePath));
    }

    public static Path getAbsolutePath(@NonNull final String fileName) {
        final String absolutePath = getAbsolutePathAsString(fileName);
        return (Paths.get(absolutePath));
    }

}
