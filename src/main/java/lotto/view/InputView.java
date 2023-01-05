package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String GET_PURCHASE_MONEY_AMOUNT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String REQUIRE_INTEGER_MESSAGE = "정수를 입력해주세요.";
    private static final String GET_WINNER_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUIRE_WINNER_TICKET_FORMAT_MESSAGE = "6개의 숫자를 ,로 구분하여 입력해주세요.";
    private static final String WINNER_TICKET_DELIMITER = ",";
    private static final int WINNER_TICKET_SIZE = 6;
    private static final String GET_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getPurchaseMoneyAmount() {
        try {
            System.out.println(GET_PURCHASE_MONEY_AMOUNT_MESSAGE);
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(REQUIRE_INTEGER_MESSAGE);
            return getPurchaseMoneyAmount();
        }
    }

    public List<Integer> getWinnerTicketNumbers() {
        try {
            System.out.println(GET_WINNER_TICKET_MESSAGE);
            String userInput = scanner.nextLine().trim();
            return convertWinnerTicketNumbers(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(REQUIRE_WINNER_TICKET_FORMAT_MESSAGE);
            return getWinnerTicketNumbers();
        }
    }

    private List<Integer> convertWinnerTicketNumbers(String userInput) {
        List<Integer> numbers = Arrays.stream(userInput.split(WINNER_TICKET_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if (numbers.size() != WINNER_TICKET_SIZE) {
            throw new IllegalArgumentException();
        }
        return numbers;
    }

    public String getBonusBall() {
        System.out.println(GET_BONUS_BALL_MESSAGE);
        return scanner.nextLine().trim();
    }
}
