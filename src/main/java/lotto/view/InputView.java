package lotto.view;

import static lotto.constant.ExceptionMessage.INPUT_BUDGET_WRONG_TYPE;
import static lotto.constant.ExceptionMessage.INPUT_LOTTERY_NUMBER_WRONG_TYPE;
import static lotto.constant.ExceptionMessage.INPUT_SELF_PICK_COUNT_WRONG_TYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final String LOTTERY_NUMBERS_DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public int readBudget() {
        String input = scanner.nextLine();
        return parseInt(input, INPUT_BUDGET_WRONG_TYPE);
    }

    public int readSelfPickCount() {
        String input = scanner.nextLine();
        return parseInt(input, INPUT_SELF_PICK_COUNT_WRONG_TYPE);
    }

    public List<List<Integer>> readSelfPickNumbers(int selfPickCount) {
        List<List<Integer>> selfPickNumbers = new ArrayList<>();
        for (int index = 0;index < selfPickCount;index++) {
            selfPickNumbers.add(readLottoNumberCombination());
        }
        return selfPickNumbers;
    }

    public List<Integer> readWinningLotteryNumberCombination() {
        return readLottoNumberCombination();
    }

    public int readBonusNumber() {
        String input = scanner.nextLine();
        return parseInt(input, INPUT_LOTTERY_NUMBER_WRONG_TYPE);
    }

    private List<Integer> readLottoNumberCombination() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(LOTTERY_NUMBERS_DELIMITER))
                .map(x -> parseInt(x, INPUT_LOTTERY_NUMBER_WRONG_TYPE))
                .collect(Collectors.toList());
    }

    private int parseInt(String input, String exceptionMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(exceptionMessage, e);
        }
    }
}
