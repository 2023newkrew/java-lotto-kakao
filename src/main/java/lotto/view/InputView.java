package lotto.view;

import lotto.model.LottoNumber;
import lotto.model.LottoTicket;

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
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return parseMoney(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return scanMoney();
        }
    }

    private int parseMoney(String moneyInput) {
        validateIsInteger(moneyInput);
        validateIsPositive(moneyInput);
        return Integer.parseInt(moneyInput);
    }

    private void validateIsPositive(String input) {
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException("0보다 큰 숫자여야 합니다");
        }
    }

    private void validateIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("정수인 숫자여야 합니다");
        }
    }

    public List<Integer> scanWinningTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요. (구분자는 , 입니다)");
        try {
            List<Integer> ticket = parseWinningTicket(sc.nextLine());
            validateTicketSize(ticket);
            return ticket;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return scanWinningTicket();
        }
    }

    private List<Integer> parseWinningTicket(String winningTicketInput) {
        try {
            return Arrays.stream(winningTicketInput.split(","))
                    .map(String::trim)
                    .map(number -> {
                        validateIsInteger(number);
                        validateLottoNumberRange(number);
                        return Integer.parseInt(number);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("로또 티켓은 ',' 구분자로 입력되어야 하며 정수여야 합니다");
        }
    }

    private void validateTicketSize(List<Integer> ticket) {
        if (ticket.size() != LottoTicket.NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 티켓은 6개의 숫자여야 합니다");
        }
        if (ticket.stream().distinct().count() != LottoTicket.NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 티켓은 중복되지 않은 6개의 숫자여야 합니다");
        }
    }

    public int scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return parseBonusNumber(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return scanBonusNumber();
        }
    }

    private int parseBonusNumber(String bonusNumberInput) {
        validateIsInteger(bonusNumberInput);
        validateLottoNumberRange(bonusNumberInput);
        return Integer.parseInt(bonusNumberInput);
    }

    private void validateLottoNumberRange(String bonusNumberInput) {
        int bonusNumber = Integer.parseInt(bonusNumberInput);
        if (bonusNumber < LottoNumber.MIN_NUMBER || bonusNumber > LottoNumber.MAX_NUMBER) {
            throw new IllegalArgumentException("로또 숫자는 1-45 사이의 숫자여야 합니다");
        }
    }
}
