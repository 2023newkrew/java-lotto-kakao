package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.utils.StringUtils;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPrice() {
        String line = scanner.nextLine();
        validateNPE(line);
        int price = parseInt(line);
        validatePrice(price);
        return price;

    }

    public List<Integer> readLottoAnswerNumbers() {
        return Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public Integer readBonusBall() {
        String line = scanner.nextLine();
        validateNPE(line);
        return parseInt(line);
    }

    private void validateNPE(String line) {
        if (StringUtils.isNullOrBlank(line)) {
            throw new IllegalArgumentException();
        }
    }

    public int parseInt(String num) {
        try {
            return Integer.parseInt(num);

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
