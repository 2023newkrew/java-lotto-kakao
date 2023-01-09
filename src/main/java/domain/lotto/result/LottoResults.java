package domain.lotto.result;

import domain.lotto.LottoConstant;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private static Map<LottoResultType, Integer> lottoResultCount = new HashMap<>();

    public LottoResults() {
        for (LottoResultType lottoResultType : LottoResultType.values()) {
            lottoResultCount.put(lottoResultType, 0);
        }
    }

    public int getResultCount(LottoResultType lottoResultType) {
        return lottoResultCount.getOrDefault(lottoResultType, 0);
    }

    public void countResult(LottoResultType resultType) {
        lottoResultCount.put(resultType, lottoResultCount.getOrDefault(resultType, 0) + 1);
    }

    public double getProfitRate() {
        long ticketNumber = lottoResultCount.keySet().stream()
                .mapToInt(lottoResultCount::get)
                .sum();

        long budget = ticketNumber * LottoConstant.LOTTO_TICKET_PRICE;

        long profit = lottoResultCount.keySet().stream()
                .mapToLong((key) -> (long) lottoResultCount.get(key) * key.getPrize())
                .sum();

        return (double)profit / budget;
    }
}
