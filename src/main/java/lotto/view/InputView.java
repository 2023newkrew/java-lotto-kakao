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
            throw new IllegalArgumentException("널 또는 빈 문자열이 올 수 없습니다.");
        }
    }

    public int parseInt(String num) {
        validateNPE(num);
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 주어져야 합니다.");
        }

    }

    private void validatePrice(int price) {
        validateRange(price);
        validateIsValid(price);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException("1000원 이상이 주어져야합니다.");
        }

    }

    private void validateIsValid(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000의 배수가 주어져야합니다.");
        }
    }
}
