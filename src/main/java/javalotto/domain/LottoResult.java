package javalotto.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoResult {
    private final Map<Rank, Integer> lottoResultMap;

    private LottoResult(Map<Rank, Integer> lottoResultMap) {
        this.lottoResultMap = lottoResultMap;
    }

    public static LottoResult of(Map<Rank, Integer> lottoResultMap) {
        return new LottoResult(lottoResultMap);
    }

    public double getRateOfReturn(PurchaseAmount purchaseAmount) {
        int returnAmount = lottoResultMap.entrySet().stream()
                .map(entry -> entry.getKey().prize * entry.getValue())
                .mapToInt(Integer::intValue)
                .sum();

        return (double) returnAmount / (double) purchaseAmount.getPurchaseAmount();
    }

    private static String entryToString(Map.Entry<Rank, Integer> entry) {
        return entry.getKey() + "- " + entry.getValue() + "개";
    }

    @Override
    public String toString() {
        return lottoResultMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().prize))
                .map(LottoResult::entryToString)
                .collect(Collectors.joining("\n"));
    }
}
