package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String GET_PURCHASE_MONEY_AMOUNT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String GET_WINNER_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String GET_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getPurchaseMoneyAmount() {
        System.out.println(GET_PURCHASE_MONEY_AMOUNT_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return getPurchaseMoneyAmount();
        }
    }

    public List<Integer> getWinnerTicketNumbers() {
        try {
            System.out.println(GET_WINNER_TICKET_MESSAGE);
            String userInput = scanner.nextLine().trim();
            String[] userInputString = userInput.split(",");
            return Arrays.stream(userInputString)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            return getWinnerTicketNumbers();
        }
    }

    public String getBonusBall() {
        System.out.println(GET_BONUS_BALL_MESSAGE);
        return scanner.nextLine().trim();
    }
}
