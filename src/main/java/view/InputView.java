package view;

import domain.lotto.LotteryNumbers;
import domain.lotto.LotteryResult;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getBudgetInput() {
        System.out.println("구입금액을 입력해 주세요.");

        return getNumberInput();
    }

    private int getNumberInput() {
        try {
            int result = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            return result;
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요");
        }
    }


    public LotteryResult getLotteryResult() {
        return new LotteryResult(getWinningNumbersInput(), getBonusNumberInput());
    }

    private LotteryNumbers getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return getLotteryNumbersInput();
    }

    private int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getNumberInput();
    }

    public int getManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요");

        return getNumberInput();
    }

    public List<LotteryNumbers> getManualLotteries(int count) {
        if (count == 0) return new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LotteryNumbers> result = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            result.add(getLotteryNumbersInput());
        }
        System.out.println();

        return result;
    }

    private LotteryNumbers getLotteryNumbersInput() {
        try {
            return new LotteryNumbers(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 반점으로 구분해 입력해주세요");
        }
    }
}
