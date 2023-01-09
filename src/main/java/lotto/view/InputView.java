package lotto.view;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String REGEX_NUMBER = ", ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static long getCapital() {
        System.out.println("구입금액을 입력해 주세요.");
        return Long.parseLong(scanner.nextLine());
    }

    public static int getManualQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
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
