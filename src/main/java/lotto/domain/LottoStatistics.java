package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {

    public static final String DELIMITER = "\n";
    public static final long DEFAULT_VALUE = 0L;

    private final Map<LottoResult, Long> lottoStatistics;
    private final int total;

    public LottoStatistics(List<LottoResult> lottoResultList) {
        lottoStatistics = getCountingMap(lottoResultList);
        total = lottoResultList.size();
    }

    private static Map<LottoResult, Long> getCountingMap(List<LottoResult> lottoResultList) {
        return lottoResultList.stream()
                .collect(Collectors.groupingBy((LottoResult x) -> x, Collectors.counting()));
    }

    public String getString() {
        return Arrays.stream(LottoResult.values())
                .filter(it -> it != LottoResult.MISS)
                .map(this::getLottoResultString)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getLottoResultString(LottoResult lottoResult) {
        return lottoResult.getString() + " - "
                + lottoStatistics.getOrDefault(lottoResult, DEFAULT_VALUE) + "개";
    }

    public long getIncome() {
        return lottoStatistics.keySet().stream()
                .mapToLong(it -> it.getPrize() * lottoStatistics.get(it))
                .sum();
    }

    public long getTotal() {
        return total;
    }
}
