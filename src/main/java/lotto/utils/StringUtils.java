package lotto.utils;

import java.util.Objects;

public class StringUtils {

    public static boolean isNullOrBlank(String line) {
        if (Objects.isNull(line) || line.isBlank()) {
            return true;
        }
        return false;
    }
}
