package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String MSG_INPUT_PURCHASE_COST = "구매금액을 입력해주세요.";
    private static final String ERR_PURCHASE_COST_MUST_BE_POSITIVE = "구매금액은 0보다 큰 수만 입력할 수 있습니다.";
    private static final String ERR_LOTTO_COUNT_MUST_BE_POSITIVE = "구매할 로또 개수는 0보다 큰 수만 입력할 수 있습니다.";
    private static final String MSG_INPUT_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해주세요.";
    private static final String MSG_INPUT_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해주세요.";
    private static final String MSG_INPUT_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해주세요.";
    private static final String ERR_LOTTO_NUMBERS_ARE_NOT_SIX = "로또 번호는 6개의 숫자여야 합니다.";
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

    public static int inputManualLottoCount() {
        System.out.println(MSG_INPUT_MANUAL_LOTTO_COUNT);
        int manualLottoCount = sc.nextInt();
        sc.nextLine();

        if (manualLottoCount <= 0) {
            throw new IllegalArgumentException(ERR_LOTTO_COUNT_MUST_BE_POSITIVE);
        }

        return manualLottoCount;
    }

    public static List<List<Integer>> inputManualLottoNumbers(int count) {
        System.out.println(MSG_INPUT_MANUAL_LOTTO_NUMBERS);
        List<List<Integer>> manualLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> manualNumbers = Stream.of(sc.nextLine().split(NUMBERS_DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (manualNumbers.size() != LOTTO_NUMBER_SIZE) {
                throw new IllegalArgumentException(ERR_LOTTO_NUMBERS_ARE_NOT_SIX);
            }
            manualLottos.add(manualNumbers);
        }

        return manualLottos;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println(MSG_INPUT_WINNING_NUMBERS);
        List<Integer> winningNumbers = Stream.of(sc.nextLine().split(NUMBERS_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (winningNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERR_LOTTO_NUMBERS_ARE_NOT_SIX);
        }

        return winningNumbers;
    }

    public static int inputBonusBall() {
        System.out.println(MSG_INPUT_BONUS_BALL);
        return sc.nextInt();
    }
}
