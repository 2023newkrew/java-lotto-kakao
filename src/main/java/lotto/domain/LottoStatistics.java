package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {

    private final Map<LottoResult, Integer> lottoStatistics = new HashMap<>();
    private final int total;

    public LottoStatistics(List<LottoResult> lottoResults) {
        initStatisticsMap();
        for (LottoResult lottoResult : lottoResults) {
            addCountOf(lottoResult);
        }
        total = lottoResults.size();
    }

    public String getString() {
        return Arrays.stream(LottoResult.values())
                .filter(it -> it != LottoResult.MISS)
                .map(this::getLottoResultString)
                .collect(Collectors.joining("\n"))
                + "\n" + getLottoIncomeRateString();
    }

    private String getLottoResultString(LottoResult lottoResult) {
        return lottoResult.getString() + " - " + lottoStatistics.get(lottoResult) + "개";
    }

    private String getLottoIncomeRateString() {
        return String.format("총 수익률은 %.2f입니다.", getLottoIncomeRate());
    }

    private float getLottoIncomeRate() {
        return Arrays.stream(LottoResult.values())
                .mapToInt(it -> it.getPrize() * lottoStatistics.get(it))
                .sum() / (float) (LottoTicket.LOTTO_TICKET_PRICE * total);
    }

    private void initStatisticsMap() {
        for (LottoResult lottoResult : LottoResult.values()) {
            lottoStatistics.put(lottoResult, 0);
        }
    }

    private void addCountOf(LottoResult lottoResult) {
        lottoStatistics.put(lottoResult, lottoStatistics.get(lottoResult)+1);
    }

}
