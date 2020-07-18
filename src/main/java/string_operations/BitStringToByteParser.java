package string_operations;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

import com.google.common.primitives.Bytes;

public final class BitStringToByteParser {

    private BitStringToByteParser() {
        throw new UnsupportedOperationException();
    }

    public static byte[] toBytes(@NonNull final List<String> bitStrings) {
        final List<Byte> list = new ArrayList<>();
        for (final String bitString : bitStrings) {
            final byte byteValue = (byte) Integer.parseInt(bitString, 2);
            list.add(byteValue);
        }
        return (Bytes.toArray(list));
    }

}
