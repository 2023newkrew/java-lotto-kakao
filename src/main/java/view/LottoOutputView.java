package view;

import domain.lotto.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoOutputView {
    public void printLottoTickets(List<LottoTicket> lottoTickets) {
        printLottoTicketPurchaseCount(lottoTickets);
        lottoTickets.stream()
                .map(LottoTicket::getLottoNumbers)
                .forEach(this::printLottoNumbers);
    }

    public void printLottoResult(Map<LottoPrize, Integer> prizeCounts, LottoEarningRate lottoEarningRate) {
        System.out.println("\n당첨 통계");
        System.out.println("----------");
        printPrizeCount(prizeCounts);
        printEarningRate(lottoEarningRate);
    }

    private void printPrizeCount(Map<LottoPrize, Integer> prizeCounts) {
        System.out.println("3개 일치 (" + LottoPrize.FIFTH_PRIZE.getPrizeMoney() + "원)-" +
                prizeCounts.get(LottoPrize.FIFTH_PRIZE) + "개");
        System.out.println("4개 일치 (" + LottoPrize.FOURTH_PRIZE.getPrizeMoney() + "원)-" +
                prizeCounts.get(LottoPrize.FOURTH_PRIZE) + "개");
        System.out.println("5개 일치 (" + LottoPrize.THIRD_PRIZE.getPrizeMoney() + "원)-" +
                prizeCounts.get(LottoPrize.THIRD_PRIZE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + LottoPrize.SECOND_PRIZE.getPrizeMoney() + "원)-" +
                prizeCounts.get(LottoPrize.SECOND_PRIZE) + "개");
        System.out.println("6개 일치 (" + LottoPrize.FIRST_PRIZE.getPrizeMoney() + "원)-" +
                prizeCounts.get(LottoPrize.FIRST_PRIZE) + "개");
    }

    private void printLottoTicketPurchaseCount(List<LottoTicket> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
    }

    private void printLottoNumbers(List<LottoNumber> lottoNumbers) {
        String numbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    private void printEarningRate(LottoEarningRate lottoEarningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoEarningRate.getEarningRate());
    }
}
