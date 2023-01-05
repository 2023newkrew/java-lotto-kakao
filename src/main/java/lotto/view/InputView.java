package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String MSG_INPUT_PURCHASE_COST = "구매금액을 입력해주세요.";
    private static final String ERR_PURCHASE_COST_MUST_BE_POSITIVE = "구매금액은 0보다 큰 수만 입력할 수 있습니다.";
    private static final String MSG_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해주세요.";
    private static final String ERR_NUMBERS_ARE_NOT_SIX = "당첨 번호는 6개의 숫자여야 합니다.";
    private static final String MSG_INPUT_BONUS_BALL = "보너스 볼을 입력해주세요.";
    private static final String NUMBERS_DELIMITER = ", ";
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final static Scanner sc = new Scanner(System.in);


    public static long inputPurchaseCost() {
        System.out.println(MSG_INPUT_PURCHASE_COST);
        long purchaseCost = sc.nextLong();
        sc.nextLine();

        if (purchaseCost <= 0) {

            throw new IllegalArgumentException(ERR_PURCHASE_COST_MUST_BE_POSITIVE);
        }

        return purchaseCost;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println(MSG_INPUT_WINNING_NUMBERS);
        List<Integer> winningNumbers = Stream.of(sc.nextLine().split(NUMBERS_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (winningNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERR_NUMBERS_ARE_NOT_SIX);
        }

        return winningNumbers;
    }

    public static int inputBonusBall() {
        System.out.println(MSG_INPUT_BONUS_BALL);
        return sc.nextInt();
    }
}
