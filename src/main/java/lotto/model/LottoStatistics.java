package lotto.model;

import lotto.model.enums.LottoResult;

import java.util.EnumMap;

public class LottoStatistics {

    private final EnumMap<LottoResult, Integer> lottoStatistics = new EnumMap<>(LottoResult.class);

    private Integer profit = 0;

    public void put(LottoResult lottoResult) {
        lottoStatistics.put(lottoResult, lottoStatistics.getOrDefault(lottoResult, 0) + 1);
        profit += lottoResult.getPrizeMoney();
    }

    public Integer get(LottoResult lottoResult) {
        return lottoStatistics.getOrDefault(lottoResult, 0);
    }

    public Integer getProfit() {
        return profit;
    }
}
