package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static InputView instance;
    private final Scanner sc;

    private InputView() {
        sc = new Scanner(System.in);
    }

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }
        return instance;
    }

    public int scanMoney() {
        System.out.print("\n구입금액을 입력해 주세요.\n");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("정수인 숫자로 입력되어야 합니다");
            return scanMoney();
        }
    }

    public int scanManualLottoTicketCount() {
        System.out.print("\n수동으로 구매할 로또 수를 입력해 주세요.\n");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("정수인 숫자로 입력되어야 합니다");
            return scanManualLottoTicketCount();
        }
    }

    public List<Integer> scanWinningTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요. (구분자는 , 입니다)");
        return scanLottoTicket();
    }

    public List<List<Integer>> scanManualLottoTickets(int ticketCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요. (구분자는 , 입니다)");
        List<List<Integer>> manualTickets = new ArrayList<>();
        while (ticketCount > 0) {
            manualTickets.add(scanLottoTicket());
            ticketCount--;
        }
        return manualTickets;
    }

    private List<Integer> scanLottoTicket() {
        try {
            return parseLottoTicket(sc.nextLine());
        } catch (Exception e) {
            System.out.println("로또 번호는 정수인 숫자여야 합니다");
            return scanLottoTicket();
        }
    }

    private List<Integer> parseLottoTicket(String lottoTicketInput) {
        return Arrays.stream(lottoTicketInput.split(",")).map(String::trim)
                .map(Integer::parseInt).collect(Collectors.toList());
    }

    public int scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("정수인 숫자로 입력되어야 합니다");
            return scanBonusNumber();
        }
    }
}
