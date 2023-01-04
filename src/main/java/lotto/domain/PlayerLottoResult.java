package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class PlayerLottoResult {

    private final int spentMoney;
    private final Map<LottoResult, Integer> lottoResults;

    public PlayerLottoResult(int spentMoney) {
        this.spentMoney = spentMoney;
        this.lottoResults = initializeLottoResults();
    }

    private Map<LottoResult, Integer> initializeLottoResults() {
        Map<LottoResult, Integer> lottoResults = new HashMap<>();
        for (LottoResult lottoResult : LottoResult.values()) {
            lottoResults.put(lottoResult, 0);
        }
        return lottoResults;
    }

    public void addResult(LottoResult lottoResult) {
        lottoResults.put(lottoResult, lottoResults.get(lottoResult) + 1);
    }

    public double calculateProfitRate() {
        return calculateTotalPrizeMoney() / (double) spentMoney;
    }

    private int calculateTotalPrizeMoney() {
        int totalMoney = 0;
        for (LottoResult result : LottoResult.values()) {
            int resultCount = lottoResults.get(result);
            totalMoney += result.getPrizeMoney() * resultCount;
        }
        return totalMoney;
    }

    public int getValue(LottoResult lottoResult) {
        return lottoResults.get(lottoResult);
    }
}
