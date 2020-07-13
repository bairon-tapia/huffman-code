package string_operations;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class ByteToBitStringParser {

    public static final int BYTE_LENGTH = 8;

    private ByteToBitStringParser() {
        throw new UnsupportedOperationException();
    }

    public static List<String> toBitStrings(@NonNull final byte[] bytes, final int lastByteLength) {
        final List<String> bitStrings = new ArrayList<>();
        final int n = bytes.length;
        for (int i = 0; i < n - 1; i++) {
            final byte byteValue = bytes[i];
            final String bitString = formatByteString(byteValue, BYTE_LENGTH);
            bitStrings.add(bitString);
        }
        final byte lastByteValue = bytes[n - 1];
        final String lastBitString = formatByteString(lastByteValue, lastByteLength);
        bitStrings.add(lastBitString);
        return (bitStrings);
    }

    public static String formatByteString(final byte byteValue, final int byteLength) {
        final String format = "%" + byteLength + "s";
        return (String.format(format, Integer.toBinaryString(byteValue & 0xFF)).replace(' ', '0'));
    }

}
