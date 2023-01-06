package lotto.view;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String REGEX_NUMBER = ", ";
    private static final Scanner scanner = new Scanner(System.in);

    public static long getCapital(long minimum) {
        long capital = 0L;
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            capital = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("올바른 수를 입력해 주세요.");
        }
        if (capital < minimum) {
            System.out.println("로또 가격 이상의 값을 입력해 주세요.");
        }
        return capital;
    }

    public static int getManualQuantity() {
        int quantity = 0;
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("올바른 수를 입력해 주세요.");
        }
        if (quantity < 0) {
            System.out.println("0 이상의 값을 입력해 주세요.");
        }
        return quantity;
    }

    private static List<LottoNumber> getNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();
        String input = scanner.nextLine();
        for (String number : input.split(InputView.REGEX_NUMBER)) {
            numbers.add(LottoNumber.get(number));
        }
        return numbers;
    }

    public static List<List<LottoNumber>> getManualNumbersList(int quantity) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<LottoNumber>> manualNumbersList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            manualNumbersList.add(InputView.getNumbers());
        }
        return manualNumbersList;
    }

    public static LottoTicket getWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return new LottoTicket(InputView.getNumbers());
    }

    public static LottoNumber getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return LottoNumber.get(input);
    }
}
