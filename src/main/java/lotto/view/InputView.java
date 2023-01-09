package lotto.view;

import lotto.domain.LottoTicket;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long getMoney() {
        ResultView.print("구입금액을 입력해 주세요.");
        return Long.parseLong(scanner.nextLine());
    }

    public static long getManualTicketQuantity(long money) {
        ResultView.print("수동으로 구매할 로또 수를 입력해 주세요.");
        long manualTicketQuantity = Long.parseLong(scanner.nextLine());
        validateMoney(money, manualTicketQuantity);
        return manualTicketQuantity;
    }

    public static List<Integer> getManualLottoNumbers() {
        ResultView.print("수동으로 구매할 번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> getWinningNumbers() {
        ResultView.print("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int getBonusNumber() {
        ResultView.print("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void validateMoney(long money, long manualLottoQuantity) {
        if (money < manualLottoQuantity * LottoTicket.LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("구매 금액이 모자랍니다.");
        }
    }
}
