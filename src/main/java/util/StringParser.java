package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
    public static List<Integer> parse(String text) {
        String[] texts = text.split(", ?");

        return Arrays.stream(texts).map(Integer::parseInt).collect(Collectors.toList());
    }
}
