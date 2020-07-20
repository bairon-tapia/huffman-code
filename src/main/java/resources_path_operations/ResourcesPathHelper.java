package resources_path_operations;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.NonNull;

public final class ResourcesPathHelper {

    private static final String SEPARATOR;
    private static final String DIRECTORY_FOLDER;
    private static final String FILE_FOLDER_RELATIVE_PATH;
    private static final String FILE_FOLDER_ABSOLUTE_PATH;

    static {
        SEPARATOR = System.getProperty("file.separator");
        DIRECTORY_FOLDER = System.getProperty("user.dir");
        FILE_FOLDER_RELATIVE_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources";
        FILE_FOLDER_ABSOLUTE_PATH = DIRECTORY_FOLDER + SEPARATOR + FILE_FOLDER_RELATIVE_PATH;
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
