package lotto.domain.lotto;

import lotto.domain.lotto.prize.LottoPrize;
import lotto.domain.lotto.store.LottoStore;
import lotto.domain.lotto.ticket.LottoTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LottoResultCalculator {

    private final List<LottoTicket> lottoTickets;
    private final LottoWinningNumber lottoWinningNumber;

    public LottoResultCalculator(List<LottoTicket> lottoTickets, LottoWinningNumber lottoWinningNumber) {
        this.lottoTickets = lottoTickets;
        this.lottoWinningNumber = lottoWinningNumber;
    }

    public LottoResult calculateLottoResult() {
        BigDecimal earningRate = calculateEarningRate();
        Map<LottoPrize, Integer> prizeCounts = countLottoPrize();

        return new LottoResult(prizeCounts, earningRate);
    }

    private Map<LottoPrize, Integer> countLottoPrize() {
        Map<LottoPrize, Integer> prizeCounts = new HashMap<>();
        Arrays.stream(LottoPrize.values())
                .forEach(prize -> prizeCounts.put(prize, 0));

        lottoTickets.stream()
                .map(lottoTicket -> LottoPrize.findPrize(lottoTicket, lottoWinningNumber))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(lottoPrize -> prizeCounts.computeIfPresent(lottoPrize, (key, value) -> value + 1));

        return prizeCounts;
    }

    private BigDecimal calculateEarningRate() {
        BigDecimal lottoCost = new BigDecimal(lottoTickets.size() * LottoStore.LOTTO_PRICE);
        BigDecimal prizeMoneySum = new BigDecimal(
                lottoTickets.stream()
                        .map(lottoTicket -> LottoPrize.findPrize(lottoTicket, lottoWinningNumber))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .mapToInt(LottoPrize::getPrizeMoney)
                        .sum()
        );

        return prizeMoneySum.divide(lottoCost, 2, RoundingMode.DOWN);
    }
}
