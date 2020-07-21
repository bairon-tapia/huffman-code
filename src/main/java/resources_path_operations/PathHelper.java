package resources_path_operations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.NonNull;

public final class PathHelper {

    private static final String SEPARATOR;
    private static final String DIRECTORY_FOLDER;
    private static final String RESOURCES_FOLDER_RELATIVE_PATH;
    private static final String RESOURCES_FOLDER_ABSOLUTE_PATH;

    static {
        SEPARATOR = System.getProperty("file.separator");
        DIRECTORY_FOLDER = System.getProperty("user.dir");
        RESOURCES_FOLDER_RELATIVE_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources";
        RESOURCES_FOLDER_ABSOLUTE_PATH = DIRECTORY_FOLDER + SEPARATOR + RESOURCES_FOLDER_RELATIVE_PATH;
    }

    private PathHelper() {
        throw new UnsupportedOperationException();
    }

    public static String getDirectoryFolderPath(@NonNull final String fileName) {
        return (DIRECTORY_FOLDER + SEPARATOR + fileName);
    }

    public static String getRelativeResourcesPathAsString(@NonNull final String fileName) {
        return (RESOURCES_FOLDER_RELATIVE_PATH + SEPARATOR + fileName);
    }

    public static String getAbsoluteResourcesPathAsString(@NonNull final String fileName) {
        return (RESOURCES_FOLDER_ABSOLUTE_PATH + SEPARATOR + fileName);
    }

    public static Path getRelativeResourcesPath(@NonNull final String fileName) {
        final String relativePath = getRelativeResourcesPathAsString(fileName);
        return (Paths.get(relativePath));
    }

    public static Path getAbsoluteResourcesPath(@NonNull final String fileName) {
        final String absolutePath = getAbsoluteResourcesPathAsString(fileName);
        return (Paths.get(absolutePath));
    }

    public static Path resolveDirectory(@NonNull final String fileName) {
        final Path path = getAbsoluteResourcesPath(fileName);
        if (!Files.exists(path)) {
            return (Paths.get(getDirectoryFolderPath(fileName)));
        }
        return (path);
    }

}
