package input_operations;

import lombok.NonNull;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class InputHelper {

    private static final char CONFIRM;
    private static final char DENY;
    private static final Scanner INPUT;

    static {
        CONFIRM = 'y';
        DENY = 'n';
        INPUT = new Scanner(System.in);
    }

    private InputHelper() {
        throw new UnsupportedOperationException();
    }

    public static void closeInput() {
        INPUT.close();
    }

    public static boolean getConfirmation(@NonNull final String message) {
        char character = getCharacter(message, CONFIRM, DENY);
        return character == CONFIRM;
    }

    public static char getCharacter(@NonNull final String message, final char characterOne, final char characterTwo) {
        char character = '\0';
        boolean loop = true;
        while (loop) {
            try {
                System.out.format("%s <%c, %c>: ", message, characterOne, characterTwo);
                character = INPUT.next(".").charAt(0);
                character = Character.toLowerCase(character);
                INPUT.nextLine();
                if ((character != characterOne) && (character != characterTwo)) {
                    System.out.println("You must enter one of the two characters specified. Try again.");
                } else {
                    loop = false;
                }
            } catch (final InputMismatchException inputMismatchException) {
                System.out.println("You must enter a character. Try again.");
                INPUT.nextLine();
            }
        }
        return (character);
    }

    public static void promptEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        if (INPUT.hasNextLine()) {
            INPUT.nextLine();
        }
    }
}
