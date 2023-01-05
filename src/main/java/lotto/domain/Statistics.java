package lotto.domain;

import lotto.util.LottoPayment;

import java.util.*;

public class Statistics {
    private final Map<LottoRank, Integer> ranks;
    private int purchase;

    public Statistics() {
        ranks = new EnumMap<>(Map.of(
                LottoRank.FIRST, 0,
                LottoRank.SECOND, 0,
                LottoRank.THIRD, 0,
                LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0,
                LottoRank.FAIL, 0
        ));
    }

    public Statistics(Lottos lottos, Lotto winLotto, LottoNumber bonusNumber) {
        this();
        for (Lotto lotto : lottos) {
            LottoResult result = new LottoResult(lotto, winLotto, bonusNumber);
            add(result.getRank());
        }
    }

    public void add(LottoRank rank) {
        ranks.merge(rank, 1, Integer::sum);
        purchase += LottoPayment.LOTTO_COST;
    }

    public int getByRank(LottoRank rank) {
        return ranks.get(rank);
    }

    public long getPrizeAmount() {
        return Arrays.stream(LottoRank.values())
                .mapToLong(rank -> ranks.get(rank) * rank.PRIZE)
                .sum();
    }

    public double getProfitRate() {
        return ((double) getPrizeAmount() / (double) purchase);
    }
}
