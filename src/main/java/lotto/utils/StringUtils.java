package lotto.utils;

import static lotto.utils.ErrorMessageFormatter.makeErrorMessage;
import static lotto.view.InputErrorMessage.NULL_OR_BLANK_EXCEPTION_MESSAGE;
import static lotto.view.InputErrorMessage.REQUIRED_NUMBER_EXCEPTION_MESSAGE;

import java.util.Objects;

public class StringUtils {

    public static boolean isNullOrBlank(String line) {
        return Objects.isNull(line) || line.isBlank();
    }

    private static void validateNPE(String line) {
        if (StringUtils.isNullOrBlank(line)) {
            throw new IllegalArgumentException(
                    makeErrorMessage(NULL_OR_BLANK_EXCEPTION_MESSAGE.getMessage(), line, "line")

            );
        }
    }

    public static int parseInt(String num) {
        validateNPE(num);
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    makeErrorMessage(REQUIRED_NUMBER_EXCEPTION_MESSAGE.getMessage(), num, "num"), e);
        }

    }
}
