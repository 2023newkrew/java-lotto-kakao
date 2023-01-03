package lotto.utils;

import java.util.Objects;

public class StringUtils {

    public static boolean isNullOrBlank(String line) {
        return Objects.isNull(line) || line.isBlank();
    }
}
