package view;

import domain.lotto.LottoNumber;
import domain.lotto.LottoResult;
import domain.lotto.prize.LottoPrize;
import domain.lotto.ticket.LottoTicket;

import java.util.*;
import java.util.stream.Collectors;

public class LottoOutputView {

    private static final String resultMessageFormat = "%s (%d원)-%d개";

    public void printLottoTickets(List<LottoTicket> lottoTickets) {
        printLottoTicketPurchaseCount(lottoTickets);
        lottoTickets.stream()
                .map(LottoTicket::getLottoNumbers)
                .forEach(this::printLottoNumbers);
    }

    public void printLottoResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("----------");
        printPrizeCount(lottoResult.getPrizeCounts());
        printEarningRate(lottoResult.getEarningRate());
    }

    private void printPrizeCount(Map<LottoPrize, Integer> prizeCounts) {
        Map<LottoPrize, String> prizeDescriptionMap = makePrizeDescription();
        List<LottoPrize> lottoPrizes = Arrays.asList(LottoPrize.values());
        Collections.reverse(lottoPrizes);

        for (LottoPrize lottoPrize : lottoPrizes) {
            String resultMessage = String.format(resultMessageFormat,
                    prizeDescriptionMap.get(lottoPrize),
                    lottoPrize.getPrizeMoney(),
                    prizeCounts.get(lottoPrize));
            System.out.println(resultMessage);
        }
    }

    private Map<LottoPrize, String> makePrizeDescription() {
        Map<LottoPrize, String> prizeDescriptionMap = new HashMap<>();
        prizeDescriptionMap.put(LottoPrize.FIFTH_PRIZE, "3개 일치");
        prizeDescriptionMap.put(LottoPrize.FOURTH_PRIZE, "4개 일치");
        prizeDescriptionMap.put(LottoPrize.THIRD_PRIZE, "5개 일치");
        prizeDescriptionMap.put(LottoPrize.SECOND_PRIZE, "5개 일치, 보너스 볼 일치");
        prizeDescriptionMap.put(LottoPrize.FIRST_PRIZE, "6개 일치");
        return prizeDescriptionMap;
    }


    private void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
    }

    private void printLottoNumbers(List<LottoNumber> lottoNumbers) {
        String numbers = lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    private void printLottoTicketPurchaseCount(List<LottoTicket> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
    }
}
