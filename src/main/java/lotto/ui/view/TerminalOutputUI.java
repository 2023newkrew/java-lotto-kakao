package lotto.ui.view;

import lotto.core.LottoTicket;
import lotto.core.Ranking;
import lotto.core.Statistics;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;

public class TerminalOutputUI {
    private final PrintStream printer;

    public TerminalOutputUI(OutputStream outputStream) {
        this.printer = new PrintStream(outputStream);
    }

    public static String formatPrice(long price) {
        return String.format(
                "%s 원",
                Long.toString(price)
                        .replaceAll("(.)(?=(.{4})+(?!.))", "$1,")
        );
    }

    public void printTickets(Collection<LottoTicket> lottoTickets) {
        printer.printf("티켓을 총 %d 장 구매했습니다.\n", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            printer.println(lottoTicket);
        }
    }

    public void printStatistics(Statistics statistics) {
        printer.println("당첨 통계");
        printer.println("----------");
        printer.printf("3개 일치 (%s)- %d개\n", formatPrice(Ranking.FIFTH.getPrice()), statistics.getOrDefault(Ranking.FIFTH, 0));
        printer.printf("4개 일치 (%s)- %d개\n", formatPrice(Ranking.FOURTH.getPrice()), statistics.getOrDefault(Ranking.FOURTH, 0));
        printer.printf("5개 일치 (%s)- %d개\n", formatPrice(Ranking.THIRD.getPrice()), statistics.getOrDefault(Ranking.THIRD, 0));
        printer.printf("5개 일치, 보너스 볼 일치 (%s)- %d개\n", formatPrice(Ranking.SECOND.getPrice()), statistics.getOrDefault(Ranking.SECOND, 0));
        printer.printf("6개 일치 (%s)- %d개\n", formatPrice(Ranking.FIRST.getPrice()), statistics.getOrDefault(Ranking.FIRST, 0));
    }


    public void printTotalMargin(long income, long outcome) {
        printer.printf("총 수익률은 %.6f 입니다.\n", ((double) outcome) / ((double) income));
    }

}
