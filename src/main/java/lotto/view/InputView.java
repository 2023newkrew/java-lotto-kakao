package lotto.view;

import static lotto.domain.LottoConstants.*;
import static lotto.domain.LottoConstants.LOTTO_MINIMUM_NON_EXISTENT_PRICE;
import static lotto.domain.LottoConstants.LOTTO_UNIT_PRICE;
import static lotto.exception.ExceptionMessages.BONUS_BALL_TYPE_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.LOTTO_ANSWER_NUMBERS_TYPE_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.NON_EXISTENT_PRICE_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.NOT_DIVISIBLE_PRICE_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.PRICE_TYPE_EXCEPTION_MESSAGE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPrice() {
        try {
            String input = scanner.nextLine();
            int price = Integer.parseInt(input);
            validatePrice(price);
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PRICE_TYPE_EXCEPTION_MESSAGE, e);
        }
    }

    public List<Integer> readLottoAnswerNumbers() {
        try {
            String input = scanner.nextLine();
            return Arrays.stream(input.split(LOTTO_NUMBERS_INPUT_DELIMITER))
                    .map(x -> Integer.parseInt(x.trim()))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LOTTO_ANSWER_NUMBERS_TYPE_EXCEPTION_MESSAGE, e);
        }
    }

    public Integer readBonusBall() {
        try {
            int bonusBall = scanner.nextInt();
            return bonusBall;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_BALL_TYPE_EXCEPTION_MESSAGE);
        }

    }

    private void validatePrice(int price) {
        validateRange(price);
        validateDivisibility(price);
    }

    private void validateRange(int price) {
        if (price <= LOTTO_MINIMUM_NON_EXISTENT_PRICE) {
            throw new IllegalArgumentException(NON_EXISTENT_PRICE_EXCEPTION_MESSAGE);
        }

    }

    private void validateDivisibility(int price) {
        if (price % LOTTO_UNIT_PRICE != ZERO_REMAINDER) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_PRICE_EXCEPTION_MESSAGE);
        }
    }
}
