package view;

import domain.LottoMatchStatistics;
import domain.LottoRank;
import domain.LottoTicket;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputView {
    private final PrintStream outputStream;

    public OutputView(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void printLottoPurchaseInfo(List<LottoTicket> purchaseLottoTickets) {
        outputStream.printf("%d개를 구매했습니다.", purchaseLottoTickets.size());
        outputStream.println();

        purchaseLottoTickets.stream()
                .forEach(outputStream::println);
    }

    public void printLottoMatchStatistics(LottoMatchStatistics lottoMatchStatistics) {
        printRankStatistics(lottoMatchStatistics);
        printRateOfReturn(lottoMatchStatistics);
    }

    private void printRankStatistics(LottoMatchStatistics lottoMatchStatistics) {
        outputStream.println();
        outputStream.println("당첨 통계");
        outputStream.println("----------");

        Map<LottoRank, Integer> rankStatistics = lottoMatchStatistics.getRankStatistics();
        Iterator<LottoRank> ranks = rankStatistics.keySet().iterator();
        while(ranks.hasNext()){
            LottoRank rank = ranks.next();
            printLottoRank(rank, rankStatistics.get(rank));
        }
    }

    private void printLottoRank(LottoRank rank, int rankCount) {
        outputStream.printf("%d개 일치", rank.getMatchCount());
        if(rank.isBonusNumberMatched()){
            outputStream.print(", 보너스 볼 일치");
        }

        outputStream.printf(" (%d)원", rank.getPrizeMoney());
        outputStream.printf(" - %d개", rankCount);
        outputStream.println();
    }

    private void printRateOfReturn(LottoMatchStatistics lottoMatchStatistics) {
        outputStream.printf("총 수익률은 %.2f입니다.", lottoMatchStatistics.getRateOfReturn());
    }
}
