package lotto.view;

import lotto.model.ranking.LottoRanking;
import lotto.model.ranking.LottoStats;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class LottoOutputView {

    public void printTicket(LottoTicket ticket) {
        System.out.println(ticket.size() + "개를 구매했습니다.");
        ticket.stream().forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(LottoNumber lotto) {
        String lottoString = lotto.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoString);
    }

    public void printResult(LottoStats stats, BigDecimal profitRate) {
        printWinningStatisticsHeader();
        printWinningPrizes(stats);
        printProfitRate(profitRate);
    }

    private void printWinningStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printWinningPrizes(LottoStats lottoStats) {
        System.out.printf("3개 일치 (5000원)- %d개%n", lottoStats.countBy(LottoRanking.FIFTH));
        System.out.printf("4개 일치 (50000원)- %d개%n", lottoStats.countBy(LottoRanking.FOURTH));
        System.out.printf("5개 일치 (150000원)- %d개%n", lottoStats.countBy(LottoRanking.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개%n", lottoStats.countBy(LottoRanking.SECOND));
        System.out.printf("6개 일치 (2000000000원)- %d개%n", lottoStats.countBy(LottoRanking.FIRST));
    }

    private void printProfitRate(BigDecimal profitRate) {
        System.out.printf("총 수익율은 %.2f입니다.", profitRate.doubleValue());
    }
}
