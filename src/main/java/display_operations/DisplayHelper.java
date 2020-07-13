package display_operations;

import input_operations.InputHelper;
import lombok.NonNull;
import tree_node.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class DisplayHelper {

    private DisplayHelper() {
        throw new UnsupportedOperationException();
    }

    public static void displayString(@NonNull final String fullString) {
        System.out.println();
        System.out.println("Displaying content of string...");
        InputHelper.promptEnterKey();
        System.out.println();
        System.out.println(fullString);
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayMap(@NonNull final Map<Character, TreeNode<Character>> map) {
        System.out.println();
        System.out.println("Displaying map of all unique characters in string with their corresponding frequencies...");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.printf("%3c - %10d%n", key, value.getFrequency()));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayMapWithRoutes(@NonNull final Map<Character, TreeNode<Character>> map) {
        System.out.println();
        System.out.println("Displaying map of all unique characters in string with their corresponding frequencies " +
                "and routes...");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.printf("%3c - %10d - %-50s%n", key, value.getFrequency(),
                value.getRoute()));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayFilteredMap(@NonNull final Map<String, TreeNode<Character>> map) {
        System.out.println();
        System.out.println("Displaying all unique characters whose corresponding routes " +
                "exceed the amount of digits of a byte (8), meaning the compression will be ineffective when handling" +
                " these characters");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.printf("%3c - %10d - %-50s%n", value.getElement(), value.getFrequency(),
                key));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayRoute(@NonNull final String route) {
        System.out.println();
        final boolean booleanValue = InputHelper.getConfirmation("Do you wish to display the full route?");
        System.out.println();
        if (booleanValue) {
            System.out.println("Displaying the full route...");
            System.out.println(route);
            System.out.println();
            InputHelper.promptEnterKey();
        }
    }

    public static void displayBitStrings(@NonNull final List<String> byteStrings) {
        System.out.println();
        final boolean booleanValue = InputHelper.getConfirmation("Do you wish to display the bytes as strings?");
        System.out.println();
        if (booleanValue) {
            System.out.println("Displaying the bytes as strings...");
            System.out.println(byteStrings);
            System.out.println();
            InputHelper.promptEnterKey();
        }
    }

    public static void displayBytes(@NonNull final byte[] bytes) {
        System.out.println();
        final boolean booleanValue = InputHelper.getConfirmation("Do you wish to display the bytes as integers?");
        System.out.println();
        if (booleanValue) {
            System.out.println("Displaying the bytes as integers...");
            System.out.println(Arrays.toString(bytes));
            System.out.println();
            InputHelper.promptEnterKey();
        }
    }

}
