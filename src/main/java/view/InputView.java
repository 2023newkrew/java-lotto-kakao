package view;

import domain.Lotto;
import utils.NumberParser;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final String NOTICE_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String NOTICE_MANUAL_AMOUNT_INPUT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String NOTICE_MANUAL_LOTTO_INPUT = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String NOTICE_WINNING_LOTTO_INPUT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String NOTICE_BONUS_NUMBER_INPUT = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public Integer getMoneyInput() {
        System.out.println(NOTICE_MONEY_INPUT);
        String input =  scanner.next();
        scanner.nextLine();
        return NumberParser.parse(input);
    }

    public Integer getManualAmountInput() {
        System.out.println(NOTICE_MANUAL_AMOUNT_INPUT);
        String input =  scanner.next();
        scanner.nextLine();
        return NumberParser.parse(input);
    }

    public List<List<Integer>> getManualLottosInput(Integer amount) {
        System.out.println(NOTICE_MANUAL_LOTTO_INPUT);
        return IntStream.range(0, amount)
                .mapToObj((i)-> getManualLottoInput())
                .collect(Collectors.toList());

    }
    private List<Integer> getManualLottoInput() {
        String input = scanner.nextLine();
        return NumberParser.splitAndParse(input);
    }

    public List<Integer> getWinningLottoInput() {
        System.out.println(NOTICE_WINNING_LOTTO_INPUT);
        String input = scanner.nextLine();
        return NumberParser.splitAndParse(input);
    }

    public Integer getBonusNumberInput() {
        System.out.println(NOTICE_BONUS_NUMBER_INPUT);
        String input =  scanner.next();
        scanner.nextLine();
        return NumberParser.parse(input);
    }

}
