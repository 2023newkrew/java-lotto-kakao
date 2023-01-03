package lotto.model.statistic;

import lotto.common.LottoResult;

import java.util.HashMap;
import java.util.Map;

public class LottoStatistic {
    private final Map<LottoResult, Integer> statisticMap;

    public LottoStatistic() {
        this.statisticMap = initializeStatisticsMap();
    }

    public Long getSumOfPrize() {
        return statisticMap.keySet().stream()
                .map((key) -> key.getPrize() * statisticMap.get(key))
                .reduce(Long::sum)
                .orElse(0L);
    }

    public void raiseCount(LottoResult key) {
        statisticMap.put(key, statisticMap.get(key) + 1);
    }

    public Integer getCount(LottoResult key) {
        return statisticMap.get(key);
    }

    private Map<LottoResult, Integer> initializeStatisticsMap() {
        Map<LottoResult, Integer> statistics = new HashMap<>();
        for (LottoResult lottoResult: LottoResult.values()) {
            statistics.put(lottoResult, 0);
        }

        return statistics;
    }


}
