package utils;

import java.util.List;
import java.util.stream.Collectors;

import static utils.ErrorMessage.LOTTO_NUMBER_IS_NUMERIC;

public class NumberParser {
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
            throw new IllegalArgumentException(LOTTO_NUMBER_IS_NUMERIC.getMessage());
        }
        return number;
    }
}
