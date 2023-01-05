package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRICE;
import static lotto.utils.ErrorMessageFormatter.makeErrorMessage;
import static lotto.utils.StringUtils.parseInt;
import static lotto.view.InputErrorMessage.BOUND_EXCEPTION_MESSAGE;

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

    public List<Integer> readLottoNumbers() {
        return Arrays.stream(scanner.nextLine().split(", ")).map(StringUtils::parseInt).collect(Collectors.toList());
    }

    public Integer readBonusBall() {
        String line = scanner.nextLine();
        return parseInt(line);
    }


    private void validatePrice(int price) {
        validateRange(price);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(makeErrorMessage(BOUND_EXCEPTION_MESSAGE.getMessage(), price, "price"));
        }

    }


    public int readPassiveLottoCount(int totalAmount) {
        String line = scanner.nextLine();
        int count = parseInt(line);
        validatePassiveLottoCount(count, totalAmount);
        return count;

    }

    private void validatePassiveLottoCount(int count, int totalAmount) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    makeErrorMessage(InputErrorMessage.INVALID_LOTTO_PASSIVE_COUNT.getMessage(), count, "count"));
        }
        if (count * LOTTO_PRICE > totalAmount) {
            throw new IllegalArgumentException(
                    makeErrorMessage(InputErrorMessage.NOT_ENOUGH_TOTAL_AMOUNT_EXCEPTION_MESSAGE.getMessage(), count,
                            "count"));
        }

    }


}
