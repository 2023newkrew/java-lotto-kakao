package lotto.view;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.prize.LottoPrize;
import lotto.domain.lotto.ticket.LottoTicket;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class LottoOutputView {

    private static final String resultMessageFormat = "%s (%d원)-%d개";

    public void printLottoTickets(List<LottoTicket> lottoTickets) {
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

    public void printBuyingManualLottoMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력 주세요.");
    }

    public void printBuyingAmounts(int manualLottoAmount, int autoLottoAmount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoAmount, autoLottoAmount);
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


    private void printEarningRate(BigDecimal earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
    }

    private void printLottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        String numbers = lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }
}
