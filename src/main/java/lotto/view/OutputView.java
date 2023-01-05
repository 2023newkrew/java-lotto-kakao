package lotto.view;

import lotto.LottoTicket;
import lotto.MatchResult;
import lotto.Ranking;

import java.util.List;

public class OutputView {
    public void printLottoTickets(List<LottoTicket> lottoTickets, int numberOfManualLotto) {
        int numberOfRandomLotto = lottoTickets.size() - numberOfManualLotto;
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", numberOfManualLotto, numberOfRandomLotto);

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public void printMatchResult(MatchResult matchResult) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.printf("3개 일치 (5000원)- %d개\n", matchResult.getCount(Ranking.FIFTH));
        System.out.printf("4개 일치 (50000원)- %d개\n", matchResult.getCount(Ranking.FOURTH));
        System.out.printf("5개 일치 (1500000원)- %d개\n", matchResult.getCount(Ranking.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30000000원)- %d개\n", matchResult.getCount(Ranking.SECOND));
        System.out.printf("6개 일치 (2000000000원)- %d개\n", matchResult.getCount(Ranking.FIRST));
        System.out.printf("총 수익률은 %f입니다.\n", matchResult.calculateProfit());
    }
}
