package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPrice() {
        try {
            int price = Integer.parseInt(scanner.nextLine());

            validatePrice(price);
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> readLottoAnswerNumbers() {
        return Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public Integer readBonusBall() {
        try {
            int bonusBall = scanner.nextInt();
            return bonusBall;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

    }

    private void validatePrice(int price) {
        validateRange(price);
        validateIsValid(price);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException();
        }

    }

    private void validateIsValid(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException();
        }
    }
}
