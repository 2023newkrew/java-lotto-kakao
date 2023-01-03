package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRICE;
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
            throw new IllegalArgumentException(NULL_OR_BLANK_EXCEPTION_MESSAGE.getMessage());
        }
    }

    public int parseInt(String num) {
        validateNPE(num);
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(REQUIRED_NUMBER_EXCEPTION_MESSAGE.getMessage());
        }

    }

    private void validatePrice(int price) {
        validateRange(price);
        validateIsValid(price);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE.getValue()) {
            throw new IllegalArgumentException(BOUND_EXCEPTION_MESSAGE.getMessage());
        }

    }

    private void validateIsValid(int price) {
        if (price % LOTTO_PRICE.getValue() != 0) {
            throw new IllegalArgumentException(MODULAR_EXCEPTION_MESSAGE.getMessage());
        }

    }
}
