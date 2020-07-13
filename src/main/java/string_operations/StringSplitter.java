package string_operations;

import com.google.common.base.Splitter;
import lombok.NonNull;

import java.util.List;

public final class StringSplitter {

    private StringSplitter() {
        throw new UnsupportedOperationException();
    }

    public static List<String> split(@NonNull final String string, final int length) {
        return (Splitter.fixedLength(length).splitToList(string));
    }

    public static int getLastStringLength(@NonNull final List<String> strings) {
        final int lastStringIndex = strings.size() - 1;
        final String lastString = strings.get(lastStringIndex);
        return (lastString.length());
    }

}
