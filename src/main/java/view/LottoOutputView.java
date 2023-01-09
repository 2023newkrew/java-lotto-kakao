package view;

import domain.lotto.*;

import java.util.Set;
import java.util.stream.Collectors;

public class LottoOutputView {

    public void printLottoTicketPurchaseCount(LottoTickets lottoTickets) {
        System.out.printf("%n수동으로 %d장, 자동으로 %d장을 구매했습니다.%n",
                lottoTickets.countManualTickets(), lottoTickets.countAutoTickets());
    }

    public void printLottoTickets(LottoTickets lottoTickets) {
        lottoTickets.getAllTickets().stream()
                .map(LottoNumbers::getNumbers)
                .forEach(this::printLottoNumbers);
    }

    public void printLottoPrizeResult(LottoPrizeResult lottoPrizeResult) {
        System.out.println(System.lineSeparator() + "당첨 통계");
        System.out.println("----------");
        printPrizeCount(lottoPrizeResult);
    }

    public void printEarningRate(LottoEarningRate lottoEarningRate) {
        System.out.printf("총 수익률은 %.2f입니다.%n", lottoEarningRate.getEarningRate());
    }

    private void printLottoNumbers(Set<LottoNumber> lottoNumbers) {
        String numbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    private void printPrizeCount(LottoPrizeResult lottoPrizeResult) {
        System.out.printf("3개 일치 (%d원)- %d개%n",
                LottoPrize.FIFTH_PRIZE.getPrizeMoney(), lottoPrizeResult.getCountOf(LottoPrize.FIFTH_PRIZE));
        System.out.printf("4개 일치 (%d원)- %d개%n",
                LottoPrize.FOURTH_PRIZE.getPrizeMoney(), lottoPrizeResult.getCountOf(LottoPrize.FOURTH_PRIZE));
        System.out.printf("5개 일치 (%d원)- %d개%n",
                LottoPrize.THIRD_PRIZE.getPrizeMoney(), lottoPrizeResult.getCountOf(LottoPrize.THIRD_PRIZE));
        System.out.printf("5개 일치, 보너스 볼 일치 (%d원)- %d개%n",
                LottoPrize.SECOND_PRIZE.getPrizeMoney(), lottoPrizeResult.getCountOf(LottoPrize.SECOND_PRIZE));
        System.out.printf("6개 일치 (%d원)- %d개%n",
                LottoPrize.FIRST_PRIZE.getPrizeMoney(), lottoPrizeResult.getCountOf(LottoPrize.FIRST_PRIZE));
    }
}
