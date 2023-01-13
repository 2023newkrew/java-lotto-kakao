package util;

import java.util.List;

public class IntegerUtil {
    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    public static List<Integer> toInteger(List<String> numbers) {
        return numbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}