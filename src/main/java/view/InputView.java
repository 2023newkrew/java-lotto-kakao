package view;

import lotto.LotteryNumbers;
import lotto.LotteryResult;
import util.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getBudgetInput() {
        System.out.println("구입금액을 입력해 주세요.");
        int budget = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return budget;
    }

    public LotteryResult getLotteryResult() {
        LotteryResult lotteryResult = new LotteryResult(getWinningNumbersInput(), getBonusNumberInput());
        System.out.println();

        return lotteryResult;
    }

    private String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    private int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int getManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요");
        int manualCount = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return manualCount;
    }

    public List<LotteryNumbers> getManualLotteries(int cnt) {
        if (cnt == 0) return new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LotteryNumbers> result = new ArrayList<>();
        for (int i = 0; i < cnt; ++i) {
            result.add(new LotteryNumbers(StringParser.parse(getManualNumbers())));
        }
        System.out.println();

        return result;
    }

    public String getManualNumbers() {
        return scanner.nextLine();
    }
}
