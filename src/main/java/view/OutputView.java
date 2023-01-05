package view;

import domain.WinningStatistics;
import domain.LottoNumber;
import domain.LottoRank;
import domain.LottoTicket;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private final PrintStream outputStream;

    public OutputView(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void printLottoPurchaseInfo(List<LottoTicket> purchaseLottoTickets) {
        outputStream.printf("%d개를 구매했습니다.", purchaseLottoTickets.size());
        outputStream.println();

        for(LottoTicket purchaseLotto : purchaseLottoTickets){
            List<LottoNumber> lottoNumbers = purchaseLotto.getLottoNumbers();
            outputStream.print("[");
            outputStream.print(joinLottoNumbers(lottoNumbers));
            outputStream.print("]");
            outputStream.println();
        }
    }

    private static String joinLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));
    }

    public void printLottoMatchStatistics(WinningStatistics winningStatistics) {
        printRankStatistics(winningStatistics);
        printRateOfReturn(winningStatistics);
    }

    private void printRankStatistics(WinningStatistics winningStatistics) {
        outputStream.println();
        outputStream.println("당첨 통계");
        outputStream.println("----------");

        Map<LottoRank, Integer> rankStatistics = winningStatistics.getRankStatistics();
        for (LottoRank rank : rankStatistics.keySet()) {
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

    private void printRateOfReturn(WinningStatistics winningStatistics) {
        outputStream.printf("총 수익률은 %.2f입니다.", winningStatistics.getRateOfReturn());
    }
}
