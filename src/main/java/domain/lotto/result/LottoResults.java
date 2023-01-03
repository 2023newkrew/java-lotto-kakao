package domain.lotto.result;

import domain.lotto.LottoMetaData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private static Map<LottoResultType, Integer> lottoResultCount = new HashMap<>();

    public LottoResults() {
        for (LottoResultType lottoResultType : LottoResultType.values()) {
            lottoResultCount.put(lottoResultType, 0);
        }
    }

    public Integer getResultCount(LottoResultType lottoResultType) {
        return lottoResultCount.getOrDefault(lottoResultType, 0);
    }

    public void countResult(LottoResultType resultType) {
        lottoResultCount.put(resultType, lottoResultCount.getOrDefault(resultType, 0) + 1);
    }

    public Float getProfitRate() {
        Integer ticketNumber = lottoResultCount.keySet().stream()
                .mapToInt(lottoResultCount::get)
                .sum();
        Integer budget = ticketNumber * LottoMetaData.LOTTO_TICKET_PRICE;

        Integer profit = lottoResultCount.keySet().stream()
                .mapToInt((key) -> lottoResultCount.get(key) * key.getPrize())
                .sum();

        return profit.floatValue() / budget;
    }
}
