package lotto.model;

import java.util.Arrays;
import java.util.EnumMap;

public class LottoStatistics {

    private final EnumMap<LottoResult, Integer> lottoStatistics;

    public LottoStatistics() {
        lottoStatistics = new EnumMap<>(LottoResult.class);
        {
            Arrays.stream(LottoResult.values())
                    .forEach(lottoResult -> lottoStatistics.put(lottoResult, 0))
            ;
        }
    }

    public void put(LottoResult lottoResult) {
        lottoStatistics.put(lottoResult, lottoStatistics.get(lottoResult) + 1);
    }

    public Integer get(LottoResult lottoResult) {
        return lottoStatistics.get(lottoResult);
    }

    public Integer getProfit() {
        int result = 0;
        for (LottoResult lottoResult : LottoResult.values()) {
            result += lottoStatistics.get(lottoResult) * lottoResult.getPrizeMoney();
        }
        return result;
    }
}
