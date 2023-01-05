package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
    public static List<Integer> getNumbers(String string) {
        return Arrays.stream(string.replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
