package string_operations;

import java.util.List;

import lombok.NonNull;

public final class StringJoiner {

    private StringJoiner() {
        throw new UnsupportedOperationException();
    }

    public static String join(@NonNull final List<String> strings) {
        return (String.join("", strings));
    }

}
