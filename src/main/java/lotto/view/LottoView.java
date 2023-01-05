package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoView implements AutoCloseable {

    private static final String GET_PURCHASE_MONEY_AMOUNT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String GET_MANUAL_LOTTO_TICKETS_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String GET_WINNER_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String GET_MANUAL_LOTTO_TICKETS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String GET_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private static final String WRONG_TYPE_ERROR_MESSAGE = "잘못된 형식입니다.";

    private final Scanner scanner;

    public LottoView() {
        this.scanner = new Scanner(System.in);
    }

    public int getPurchaseMoneyAmount() {
        System.out.println(GET_PURCHASE_MONEY_AMOUNT_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printErrorMessage(WRONG_TYPE_ERROR_MESSAGE);
            return getPurchaseMoneyAmount();
        }
    }

    public void printPurchaseTickets(List<LottoTicket> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoTicket(lottoTicket);
        }
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        String message = "[";
        Set<LottoBall> lottoBalls = lottoTicket.getLottoBalls();
        List<String> lottoBallNumbers = lottoBalls.stream()
                .map(lottoBall -> lottoBall.getNumber())
                .sorted()
                .map(lottoBallNumber -> lottoBallNumber.toString())
                .collect(Collectors.toList());
        message += String.join(", ", lottoBallNumbers);
        message += "]";
        System.out.println(message);
    }

    public String getWinnerTicket() {
        try {
            System.out.println(GET_WINNER_TICKET_MESSAGE);
            String input = scanner.nextLine().trim();
            // parseInt 가능한지 확인
            for (String number : input.split(",")) {
                Integer.parseInt(number.trim());
            }
            return input;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE);
        }
    }

    public String getBonusBall() {
        try {
            System.out.println(GET_BONUS_BALL_MESSAGE);
            String input = scanner.nextLine().trim();
            // parseInt 가능한지 확인
            Integer.parseInt(input.trim());
            return input;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE);
        }
    }

    public void printStats(PlayerLottoResult playerLottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoResult lottoResult : LottoResult.values()) {
            printLottoResult(playerLottoResult, lottoResult);
        }
        String formattedProfitRate = String.format("%.2f", playerLottoResult.calculateProfitRate());
        System.out.println("총 수익률은 " + formattedProfitRate + "% 입니다.");
    }

    private void printLottoResult(PlayerLottoResult playerLottoResult, LottoResult lottoResult) {
        if (lottoResult.getMatchCount() == 0) {
            return;
        }
        if (lottoResult.getMatchCount() == 5 && lottoResult.isBonusBallMatch()) {
            printLottoResultFiveMatchCountWithBonus(playerLottoResult, lottoResult);
        }
        System.out.println(lottoResult.getMatchCount() + "개 일치 (" + lottoResult.getMoney().getAmount() + "원)- "
                + playerLottoResult.getValue(lottoResult) + "개");
    }

    private void printLottoResultFiveMatchCountWithBonus(PlayerLottoResult playerLottoResult, LottoResult lottoResult) {
        System.out.println(lottoResult.getMatchCount() + "개 일치, 보너스 볼 일치 (" + lottoResult.getMoney().getAmount() + "원)- "
                + playerLottoResult.getValue(lottoResult) + "개");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }

    public int getManualLottoTicketsCount() {
        System.out.println(GET_MANUAL_LOTTO_TICKETS_COUNT_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printErrorMessage(WRONG_TYPE_ERROR_MESSAGE);
            return getManualLottoTicketsCount();
        }
    }

    public String getManualLottoTickets() {
        try {
            String input = scanner.nextLine().trim();
            // parseInt 가능한지 확인
            for (String number : input.split(",")) {
                Integer.parseInt(number.trim());
            }
            return input;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE);
        }
    }

    public void printManualLottoTicketsInputMessage() {
        System.out.println(GET_MANUAL_LOTTO_TICKETS_MESSAGE);
    }
}
