package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
    public static List<Integer> getNumbers(String string) {
        string = string.replaceAll("\\s", "");

        if(!string.matches("^[0-9,]*$"))
            throw new IllegalArgumentException("숫자와 콤마(,)만 입력해주세요");

        return Arrays.stream(string.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
