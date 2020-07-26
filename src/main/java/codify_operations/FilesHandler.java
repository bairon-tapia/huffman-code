package codify_operations;

import java.io.IOException;
import java.nio.file.Path;

import lombok.NonNull;

import file_operations.BinaryFilesHelper;
import file_operations.FilesHelper;
import file_operations.TextFileHelper;
import input_operations.InputHelper;
import readable_byte_size_operations.ReadableByteSizeHelper;
import resources_path_operations.PathHelper;

public final class FilesHandler {

    private static final String INPUT_FILE_NAME;
    private static final String BINARY_FILE_NAME;
    private static final String OUTPUT_FILE_NAME;
    private static final Path INPUT_FILE_PATH;
    private static final Path BINARY_FILE_PATH;
    private static final Path OUTPUT_FILE_PATH;
    private static final String DEFAULT_STRING;

    static {
        INPUT_FILE_NAME = "Input.txt";
        BINARY_FILE_NAME = "Output.bin";
        OUTPUT_FILE_NAME = "Output.txt";
        DEFAULT_STRING = "This is a file for test purposes. See src/java/resources/example files to see how the " +
                "program handles the compression/decompression of other files.";
        INPUT_FILE_PATH = PathHelper.resolveWorkingDirectory(INPUT_FILE_NAME);
        BINARY_FILE_PATH = PathHelper.resolveWorkingDirectory(BINARY_FILE_NAME);
        OUTPUT_FILE_PATH = PathHelper.resolveWorkingDirectory(OUTPUT_FILE_NAME);
    }

    private FilesHandler() {
        throw new UnsupportedOperationException();
    }

    public static void displayIntroduction() {
        System.out.println("Welcome! This program is based on Huffman's algorithm to compress or decompress files.");
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println("A requirement for this code to work is to have a text (.txt) file with some text. This " +
                "will be our original input file.");
        System.out.println("The program will then compress the file in a binary (.bin) format. This will be our " +
                "compressed binary file.");
        System.out.println("Finally, the program will decompress the binary file, reconstruct the text from the " +
                "original file and write it into a text (.txt) file. This will be our decompressed output file.");
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println("The input file will be located in " + INPUT_FILE_PATH.toAbsolutePath());
        System.out.println("The binary file will be located in " + BINARY_FILE_PATH.toAbsolutePath());
        System.out.println("The output file will be located in " + OUTPUT_FILE_PATH.toAbsolutePath());
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println("If the input file does not exist or if it exists, but it is empty, the program will " +
                "automatically set up one with a default text for you.");
        System.out.println("This is done for demonstration purposes only. If you are interested in seeing how a " +
                "specific text can be compressed and then decompressed, obviously you will need to provide one " +
                "yourself.");
        System.out.println("Do not worry about either the binary file, and the output file. The program will set them" +
                " up for you.");
        System.out.println("That's it for the introduction. Let's get ready to begin!");
        InputHelper.promptEnterKey();
        System.out.println();
    }

    private static void setUpInputFile() {
        System.out.println();
        System.out.println("Attempting to either write into input file if it is empty or create input file and write " +
                "into it if it does not exist...");
        InputHelper.promptEnterKey();
        System.out.println();
        boolean methodWasUsed = false;
        try {
            methodWasUsed = TextFileHelper.writeIfNotExistsOrIsEmpty(INPUT_FILE_PATH, DEFAULT_STRING);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to set up input file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        if (methodWasUsed) {
            System.out.println("Input file set up successfully...");
        } else {
            System.out.println("The input file already exists and is not empty. No operations were necessary...");
        }
        InputHelper.promptEnterKey();
    }

    private static void setUpBinaryFile() {
        System.out.println();
        System.out.println("Attempting to either create the binary file or empty its content if it already exists...");
        InputHelper.promptEnterKey();
        System.out.println();
        boolean methodWasUsed = false;
        try {
            methodWasUsed = FilesHelper.createOrClearIfExists(BINARY_FILE_PATH);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to set up binary file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        if (methodWasUsed) {
            System.out.println("Binary file set up successfully...");
        } else {
            System.out.println("The binary file already exists and is empty. No operations were necessary...");
        }
        InputHelper.promptEnterKey();
    }

    private static void setUpOutputFile() {
        System.out.println();
        System.out.println("Attempting to either create the output file or empty its content if it already exists...");
        InputHelper.promptEnterKey();
        System.out.println();
        boolean methodWasUsed = false;
        try {
            methodWasUsed = FilesHelper.createOrClearIfExists(OUTPUT_FILE_PATH);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to set up output file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        if (methodWasUsed) {
            System.out.println("Output file set up successfully...");
        } else {
            System.out.println("The output file already exists and is empty. No operations were necessary...");
        }
        InputHelper.promptEnterKey();
    }

    public static void setUpFiles() {
        setUpInputFile();
        setUpBinaryFile();
        setUpOutputFile();
    }

    public static void displayFileSizes() {
        long inputFileSize = 0L;
        long binaryFileSize = 0L;
        long outputFileSize = 0L;
        try {
            inputFileSize = FilesHelper.getFileSize(INPUT_FILE_PATH);
            binaryFileSize = FilesHelper.getFileSize(BINARY_FILE_PATH);
            outputFileSize = FilesHelper.getFileSize(OUTPUT_FILE_PATH);
        } catch (final IOException iOException) {
            System.out.println();
            System.err.println("Critical error: Failed to get size from one of the files.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        System.out.println();
        System.out.println("The original file's (input file) size is " + ReadableByteSizeHelper.toBinUnits(inputFileSize));
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println("The compressed file's (binary file) size is " + ReadableByteSizeHelper.toBinUnits(binaryFileSize));
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println("The decompressed file's (output file) size is " + ReadableByteSizeHelper.toBinUnits(outputFileSize));
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.printf("The compression ratio achieved is %.2f : 1%n",
                (double) inputFileSize / (double) binaryFileSize);
        InputHelper.promptEnterKey();
    }

    public static String readString() {
        System.out.println();
        System.out.println("Attempting to read from input file...");
        InputHelper.promptEnterKey();
        System.out.println();
        String string = null;
        try {
            string = TextFileHelper.readString(INPUT_FILE_PATH);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to set up input file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        System.out.println("Successfully read file and loaded its contents onto a string...");
        InputHelper.promptEnterKey();
        return (string);
    }

    public static void writeString(@NonNull final String string) {
        System.out.println();
        System.out.println("Attempting to write reconstructed string into output file...");
        InputHelper.promptEnterKey();
        System.out.println();
        try {
            TextFileHelper.writeString(OUTPUT_FILE_PATH, string);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to write string into output file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        System.out.println("String written into output file successfully...");
        InputHelper.promptEnterKey();
    }

    public static void encode(@NonNull final byte[] bytes) {
        System.out.println();
        System.out.println("Attempting to write bytes into the binary file.");
        InputHelper.promptEnterKey();
        System.out.println();
        try {
            BinaryFilesHelper.writeBytes(BINARY_FILE_PATH, bytes);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to write bytes into the file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        System.out.println("Bytes written into file successfully.");
        InputHelper.promptEnterKey();
    }

    public static byte[] decode() {
        System.out.println();
        System.out.println("Attempting to read bytes from the binary file...");
        InputHelper.promptEnterKey();
        System.out.println();
        byte[] decodedBytes = null;
        try {
            decodedBytes = BinaryFilesHelper.readBytes(BINARY_FILE_PATH);
        } catch (final IOException iOException) {
            System.err.println("Critical error: Failed to read bytes from the file.");
            iOException.printStackTrace();
            System.err.println("Exiting now...");
            InputHelper.promptEnterKey();
            System.exit(1);
        }
        System.out.println("Bytes read from file successfully.");
        InputHelper.promptEnterKey();
        return (decodedBytes);
    }

}
