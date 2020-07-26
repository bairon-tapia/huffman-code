package display_operations;

import java.util.List;
import java.util.Map;

import lombok.NonNull;

import com.google.common.io.BaseEncoding;

import input_operations.InputHelper;
import tree_node.TreeNode;

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

    public static void displayMap(@NonNull final Map<Character, TreeNode> map) {
        System.out.println();
        System.out.println("Displaying map of all unique characters in string with their corresponding frequencies...");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.println(value));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayMapWithRoutes(@NonNull final Map<Character, TreeNode> map) {
        System.out.println();
        System.out.println("Displaying map of all unique characters in string with their corresponding frequencies " +
                "and routes...");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.println(value));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayFilteredMap(@NonNull final Map<String, TreeNode> map) {
        if (map.isEmpty()) {
            return;
        }
        System.out.println();
        System.out.println("Displaying all unique characters whose corresponding routes  are equal or greater than " +
                "the amount of digits of a byte (8), meaning the compression will be ineffective when handling " +
                "these characters.");
        InputHelper.promptEnterKey();
        map.forEach((key, value) -> System.out.println(value));
        System.out.println();
        InputHelper.promptEnterKey();
    }

    public static void displayHuffmanTree(@NonNull final TreeNode treeNode) {
        System.out.println();
        System.out.println("Displaying the huffman tree...");
        InputHelper.promptEnterKey();
        System.out.println(TreeNode.traversal(treeNode));
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
        final boolean booleanValue = InputHelper.getConfirmation("Do you wish to display the bytes as hexadecimals?");
        System.out.println();
        if (booleanValue) {
            System.out.println("Displaying the bytes as hexadecimals...");
            System.out.println("[" + BaseEncoding
                    .base16()
                    .upperCase()
                    .withSeparator(", ", 2)
                    .encode(bytes) + "]");
            System.out.println();
            InputHelper.promptEnterKey();
        }
    }

}
