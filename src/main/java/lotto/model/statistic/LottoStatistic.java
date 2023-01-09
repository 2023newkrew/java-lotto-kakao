package lotto.model.statistic;

import lotto.model.enums.LottoResultType;

import java.util.HashMap;
import java.util.Map;

public class LottoStatistic {
    private final Map<LottoResultType, Integer> statisticMap;
    private final Integer lottoPrice;
    private Integer totalNumberOfLotto;

    public LottoStatistic(Integer lottoPrice) {
        this.lottoPrice = lottoPrice;
        this.statisticMap = initializeStatisticsMap();
    }

    public Long getSumOfPrize() {
        return statisticMap.keySet().stream()
                .map((key) -> key.getPrize() * statisticMap.get(key))
                .reduce(Long::sum)
                .orElse(0L);
    }

    public double getPrizeRate() {
        int sumOfLottoPrice = lottoPrice * totalNumberOfLotto;
        if(sumOfLottoPrice <= 0) {
            return 0;
        }
        return (double) getSumOfPrize() / sumOfLottoPrice;
    }

    public void raiseCount(LottoResultType key) {
        totalNumberOfLotto += 1;

        statisticMap.put(key, statisticMap.get(key) + 1);
    }

    public Integer getCount(LottoResultType key) {
        return statisticMap.get(key);
    }

    private Map<LottoResultType, Integer> initializeStatisticsMap() {
        Map<LottoResultType, Integer> statistics = new HashMap<>();
        for (LottoResultType lottoResultType : LottoResultType.values()) {
            statistics.put(lottoResultType, 0);
        }

        return statistics;
    }


}
