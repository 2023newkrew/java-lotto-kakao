package utils;

import java.util.List;
import java.util.stream.Collectors;

public class NumberParser {
    private static final String ERROR_INPUT_NOT_INTEGER = "로또 숫자는 정수만 가능합니다. 정수를 입력해주세요.";
    public static List<Integer> splitAndParse(String input) {
        List<String> result = List.of(input.split(", "));
        return result
                .stream()
                .map(NumberParser::parse)
                .collect(Collectors.toList());
    }

    public static Integer parse(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INPUT_NOT_INTEGER);
        }
        return number;
    }
}
