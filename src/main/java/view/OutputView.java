package view;

import domain.*;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import domain.LottoTicketStore.LottoTicket;

import static domain.LottoRank.*;

public class OutputView {
    private final PrintStream outputStream;
    private final List<LottoRank> statisticsTargetRanks = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH);

    public OutputView(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void printUserInfo(User user) {
        outputStream.println();
        outputStream.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.",
                user.getManualTickets().size(), user.getAutoTickets().size());
        outputStream.println();

        printLottoTickets(user.getManualTickets());
        printLottoTickets(user.getAutoTickets());
    }

    private void printLottoTickets(List<LottoTicket> lottoTickets) {
        for(LottoTicket lottoTicket : lottoTickets){
            LottoNumbers lottoNumbers = lottoTicket.getLottoNumbers();
            outputStream.print("[");
            outputStream.print(joinLottoNumbers(lottoNumbers));
            outputStream.print("]");
            outputStream.println();
        }
    }

    private static String joinLottoNumbers(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumbers().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));
    }

    public void printWinningStatistics(WinningStatistics winningStatistics) {
        printRankStatistics(winningStatistics);
        printRateOfReturn(winningStatistics);
    }

    private void printRankStatistics(WinningStatistics winningStatistics) {
        outputStream.println();
        outputStream.println("당첨 통계");
        outputStream.println("----------");

        for(LottoRank lottoRank : statisticsTargetRanks){
            printLottoRank(lottoRank, winningStatistics.getRankCount(lottoRank));
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
