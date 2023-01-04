package javalotto.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<Rank, Integer> lottoResultMap;

    private LottoResult(Map<Rank, Integer> lottoResultMap) {
        this.lottoResultMap = lottoResultMap;
    }

    public static LottoResult of(Map<Rank, Integer> lottoResultMap) {
        return new LottoResult(lottoResultMap);
    }

    private static String entryToString(Map.Entry<Rank, Integer> entry) {
        return entry.getKey() + "- " + entry.getValue() + "개";
    }

    public Double getTotalRateOfReturn(PurchaseAmount purchaseAmount) {
        long totalPrize = lottoResultMap.entrySet().stream()
                .mapToLong(LottoResult::getEachSumOfPrize)
                .sum();
        return totalPrize / (double) purchaseAmount.getPurchaseAmount();
    }

    private static long getEachSumOfPrize(Map.Entry<Rank, Integer> entry) {
        return (long) entry.getKey().prize * entry.getValue();
    }

    @Override
    public String toString() {
        return lottoResultMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().prize))
                .map(LottoResult::entryToString)
                .collect(Collectors.joining("\n"));
    }
}
