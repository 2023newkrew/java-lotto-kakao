package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
    public static List<Integer> parse(String text) {
        String[] texts = text.split(", ?");

        try {
            return Arrays.stream(texts).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 반점으로 구분해 입력해주세요");
        }
    }
}
