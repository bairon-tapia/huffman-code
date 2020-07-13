package string_operations;

import lombok.NonNull;

import java.util.List;

public final class StringJoiner {

    private StringJoiner() {
        throw new UnsupportedOperationException();
    }

    public static String join(@NonNull final List<String> strings) {
        return (String.join("", strings));
    }

}
