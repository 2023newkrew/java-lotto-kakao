package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String PAYMENT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해주세요";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public int getUserInputPayment() {
        System.out.println(PAYMENT_INPUT_MESSAGE);
        return getIntValue();
    }

    public List<Integer> getUserInputLottoNumbers() {
        System.out.println(LOTTO_NUMBERS_INPUT_MESSAGE);

        return Arrays.stream(scanner.nextLine().split(LOTTO_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getUserInputBonusBallNumbers() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return getIntValue();
    }

    private int getIntValue() {
        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

}
