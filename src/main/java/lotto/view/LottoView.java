package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.PlayerLottoResult;

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

    public String getPurchaseMoneyAmount() {
        return getInput(GET_PURCHASE_MONEY_AMOUNT_MESSAGE);
    }

    public String getManualLottoTicketsCount() {
        return getInput(GET_MANUAL_LOTTO_TICKETS_COUNT_MESSAGE);
    }

    public String getManualLottoTickets() {
        return getInput(null);
    }

    public void printPurchaseTickets(int manualLottoTicketsCount, List<LottoTicket> lottoTickets) {
        int autoLottoTicketsCount = lottoTickets.size() - manualLottoTicketsCount;
        System.out.println("수동으로 " + manualLottoTicketsCount + "장, 자동으로 " + autoLottoTicketsCount + "개를 구매했습니다.");
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
        return getInput(GET_WINNER_TICKET_MESSAGE);
    }

    public String getBonusBall() {
        return getInput(GET_BONUS_BALL_MESSAGE);
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

    public void printManualLottoTicketsInputMessage() {
        System.out.println(GET_MANUAL_LOTTO_TICKETS_MESSAGE);
    }

    private String getInput(String informMessage) {
        try {
            printInformMessage(informMessage);
            String input = scanner.nextLine().trim();
            checkParsableToInt(input);
            return input;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE);
        }
    }

    private void printInformMessage(String informMessage) {
        if (informMessage != null && !informMessage.isBlank()) {
            System.out.println(informMessage);
        }
    }

    private void checkParsableToInt(String input) throws NumberFormatException {
        for (String number : input.split(",")) {
            Integer.parseInt(number.trim());
        }
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
