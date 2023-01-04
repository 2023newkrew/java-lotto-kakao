package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRICE;
import static lotto.exception.ErrorMessageFormatter.makeErrorMessage;
import static lotto.view.InputErrorMessage.BOUND_EXCEPTION_MESSAGE;
import static lotto.view.InputErrorMessage.MODULAR_EXCEPTION_MESSAGE;
import static lotto.view.InputErrorMessage.NULL_OR_BLANK_EXCEPTION_MESSAGE;
import static lotto.view.InputErrorMessage.REQUIRED_NUMBER_EXCEPTION_MESSAGE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.utils.StringUtils;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPrice() {
        String line = scanner.nextLine();
        int price = parseInt(line);
        validatePrice(price);
        return price;
    }

    public List<Integer> readLottoAnswerNumbers() {
        return Arrays.stream(scanner.nextLine().split(", ")).map(this::parseInt).collect(Collectors.toList());
    }

    public Integer readBonusBall() {
        String line = scanner.nextLine();
        return parseInt(line);
    }

    private void validateNPE(String line) {
        if (StringUtils.isNullOrBlank(line)) {
            throw new IllegalArgumentException(
                    makeErrorMessage(NULL_OR_BLANK_EXCEPTION_MESSAGE.getMessage(), line, "line")

            );
        }
    }

    public int parseInt(String num) {
        validateNPE(num);
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    makeErrorMessage(REQUIRED_NUMBER_EXCEPTION_MESSAGE.getMessage(), num, "num"), e);
        }

    }

    private void validatePrice(int price) {
        validateRange(price);
        validateIsValid(price);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(makeErrorMessage(BOUND_EXCEPTION_MESSAGE.getMessage(), price, "price"));
        }

    }

    private void validateIsValid(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    makeErrorMessage(MODULAR_EXCEPTION_MESSAGE.getMessage(), price, "price"));
        }

    }
}
