package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.LottoTicket;
import lotto.domain.Player;
import lotto.domain.PlayerLottoResult;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.LottoResult.*;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String LOTTO_BOUGHT_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.\n";
    private static final String LOTTO_NUMBER_PRINT_OPENER = "[";
    private static final String LOTTO_NUMBER_JOIN_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_PRINT_CLOSER = "]\n";
    private static final String LOTTO_RESULT_STATUS = "당첨 통계\n";
    private static final String LOTTO_RESULT_LINE = "---------\n";
    private static final String LOTTO_RESULT_FIFTH_PRIZE = "3개 일치 (5000원)- %d개\n";
    private static final String LOTTO_RESULT_FOURTH_PRIZE = "4개 일치 (50000원)- %d개\n";
    private static final String LOTTO_RESULT_THIRD_PRIZE = "5개 일치 (1500000원)- %d개\n";
    private static final String LOTTO_RESULT_SECOND_PRIZE = "5개 일치, 보너스 볼 일치(30000000원) - %d개\n";
    private static final String LOTTO_RESULT_FIRST_PRIZE = "6개 일치 (2000000000원)- %d개\n";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    public void printPurchasedTickets(Player player) {
        StringBuilder purchaseStatus = new StringBuilder();
        List<LottoTicket> manualLottoTickets = player.getManualLottoTickets();
        List<LottoTicket> autoLottoTickets = player.getAutoLottoTickets();
        purchaseStatus.append(NEW_LINE);
        purchaseStatus.append(String.format(LOTTO_BOUGHT_MESSAGE, manualLottoTickets.size(), autoLottoTickets.size()));
        for (LottoTicket manualLottoTicket : manualLottoTickets) {
            purchaseStatus.append(purchasedTicketStatus(manualLottoTicket));
        }
        for (LottoTicket autoLottoTicket : autoLottoTickets) {
            purchaseStatus.append(purchasedTicketStatus(autoLottoTicket));
        }
        System.out.println(purchaseStatus);
    }

    private String purchasedTicketStatus(LottoTicket lottoTicket) {
        List<String> lottoBallNumbers = lottoTicket.getLottoBalls()
                .stream()
                .map(LottoBall::getNumber)
                .map(String::valueOf)
                .collect(Collectors.toList());
        return LOTTO_NUMBER_PRINT_OPENER +
                String.join(LOTTO_NUMBER_JOIN_DELIMITER, lottoBallNumbers) +
                LOTTO_NUMBER_PRINT_CLOSER;
    }

    public void printStats(PlayerLottoResult playerLottoResult) {
        String result = NEW_LINE +
                LOTTO_RESULT_STATUS +
                LOTTO_RESULT_LINE +
                String.format(LOTTO_RESULT_FIFTH_PRIZE, playerLottoResult.getValue(FIFTH_PLACE)) +
                String.format(LOTTO_RESULT_FOURTH_PRIZE, playerLottoResult.getValue(FOURTH_PLACE)) +
                String.format(LOTTO_RESULT_THIRD_PRIZE, playerLottoResult.getValue(THIRD_PLACE)) +
                String.format(LOTTO_RESULT_SECOND_PRIZE, playerLottoResult.getValue(SECOND_PLACE)) +
                String.format(LOTTO_RESULT_FIRST_PRIZE, playerLottoResult.getValue(FIRST_PLACE)) +
                String.format(PROFIT_RATE_FORMAT, playerLottoResult.calculateProfitRate());
        System.out.print(result);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
