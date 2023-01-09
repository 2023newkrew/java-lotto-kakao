package lotto.view;

import lotto.model.ranking.AnalysisResult;
import lotto.model.ranking.LottoRanking;
import lotto.model.store.PurchaseResult;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.stream.Collectors;

public class LottoOutputView {

    public void printPurchaseResult(PurchaseResult purchaseResult) {
        LottoTicket ticket = purchaseResult.getTicket();
        System.out.println(ticket.count() + "개를 구매했습니다.");
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

    public void printAnalysisResult(AnalysisResult analysisResult) {
        printAnalysisResultHeader();
        printRankingCounts(analysisResult);
        printProfitRate(analysisResult.getProfitRate());
    }

    private void printAnalysisResultHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printRankingCounts(AnalysisResult analysisResult) {
        System.out.printf("3개 일치 (5000원)- %d개%n", analysisResult.countBy(LottoRanking.FIFTH));
        System.out.printf("4개 일치 (50000원)- %d개%n", analysisResult.countBy(LottoRanking.FOURTH));
        System.out.printf("5개 일치 (150000원)- %d개%n", analysisResult.countBy(LottoRanking.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개%n", analysisResult.countBy(LottoRanking.SECOND));
        System.out.printf("6개 일치 (2000000000원)- %d개%n", analysisResult.countBy(LottoRanking.FIRST));
    }

    private void printProfitRate(double profitRate) {
        System.out.printf("총 수익율은 %.2f입니다.", profitRate);
    }
}
