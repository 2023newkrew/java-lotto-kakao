package javalotto.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<Rank, Long> lottoResultMap;

    private LottoResult(Map<Rank, Long> lottoResultMap) {
        this.lottoResultMap = lottoResultMap;
    }

    public static LottoResult from(Map<Rank, Long> lottoResultMap) {
        return new LottoResult(lottoResultMap);
    }

    public long getTotalPrizeAmount() {
        return lottoResultMap.entrySet().stream()
                .map(this::prizeSumOfEntry)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long prizeSumOfEntry(Map.Entry<Rank, Long> entry) {
        return entry.getKey().getPrize() * entry.getValue();
    }

    private static String entryToString(Map.Entry<Rank, Long> entry) {
        return entry.getKey() + "- " + entry.getValue() + "개";
    }

    @Override
    public String toString() {
        return lottoResultMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getPrize()))
                .map(LottoResult::entryToString)
                .collect(Collectors.joining("\n"));
    }
}
