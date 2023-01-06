package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String PAYMENT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NUMBER_OF_LOTTO_BUY_MANUALLY_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String LOTTO_NUMBERS_MANUALLY_INPUT_MESSAGE = "\n수동으로 구매할 번호를 입력해주세요.";
    private static final String LOTTO_NUMBERS_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해주세요";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public int getPayment() {
        System.out.println(PAYMENT_INPUT_MESSAGE);
        return getIntValue();
    }

    public int getManualLottoCount() {
        System.out.println(NUMBER_OF_LOTTO_BUY_MANUALLY_MESSAGE);
        return getIntValue();
    }

    public List<List<Integer>> getManualLottoNumbers(int num) {
        System.out.println(LOTTO_NUMBERS_MANUALLY_INPUT_MESSAGE);

        return IntStream.range(0, num)
                .mapToObj(i -> getLottoNumbers())
                .collect(Collectors.toList());
    }

    public List<Integer> getWinningLottoNumbers() {
        System.out.println(LOTTO_NUMBERS_INPUT_MESSAGE);
        return getLottoNumbers();
    }

    public int getBonusBallNumber() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return getIntValue();
    }

    private List<Integer> getLottoNumbers() {
        return Arrays.stream(scanner.nextLine().split(LOTTO_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getIntValue() {
        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

}
