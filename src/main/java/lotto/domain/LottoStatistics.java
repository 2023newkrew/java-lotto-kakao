package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {

    public static final String DELIMITER = "\n";
    public static final long DEFAULT_VALUE = 0L;

    private final Map<LottoResult, Long> lottoStatistics;
    private final int totalLottoResult;

    public LottoStatistics(List<LottoResult> lottoResultList) {
        lottoStatistics = getCountingMap(lottoResultList);
        totalLottoResult = lottoResultList.size();
    }

    private static Map<LottoResult, Long> getCountingMap(List<LottoResult> lottoResultList) {
        return lottoResultList.stream()
                .collect(Collectors.groupingBy((LottoResult x) -> x, Collectors.counting()));
    }

    public String getString() {
        return Arrays.stream(LottoResult.values())
                .filter(it -> it != LottoResult.MISS)
                .map(this::getLottoResultString)
                .collect(Collectors.joining(DELIMITER))
                + "\n" + getLottoIncomeRateString();
    }

    private String getLottoResultString(LottoResult lottoResult) {
        return lottoResult.getString() + " - "
                + lottoStatistics.getOrDefault(lottoResult, DEFAULT_VALUE) + "개";
    }

    private String getLottoIncomeRateString() {
        return String.format("총 수익률은 %.2f입니다.", getLottoIncomeRate());
    }

    private float getLottoIncomeRate() {
        return lottoStatistics.keySet().stream()
                .mapToLong(it -> it.getPrize() * lottoStatistics.get(it))
                .sum() / (float) (LottoTicket.LOTTO_TICKET_PRICE * totalLottoResult);
    }
}
