package domain;

import java.util.HashMap;
import java.util.Map;

public class PlayerLottoResult {

    private final Money initialMoney;
    private final Map<LottoResult, Integer> lottoResults;

    public PlayerLottoResult(Money initialMoney) {
        this.initialMoney = initialMoney;
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
        return calculateTotalPrizeMoney().divideBy(initialMoney);
    }

    private Money calculateTotalPrizeMoney() {
        Money totalMoney = new Money(0);
        for (LottoResult result : LottoResult.values()) {
            int resultCount = lottoResults.get(result);
            Money resultPrizeMoney = result.getMoney().multiply(resultCount);
            totalMoney = totalMoney.add(resultPrizeMoney);
        }
        return totalMoney;
    }
}
