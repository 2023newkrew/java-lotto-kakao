package lotto.domain;

import lotto.domain.enumeration.LottoResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {

    private final Map<LottoResult, Integer> lottoHistogram = new HashMap<>();
    private final int total;

    public LottoStatistics(List<LottoResult> lottoResults) {
        initStatisticsMap();
        for (LottoResult lottoResult : lottoResults) {
            addCountOf(lottoResult);
        }
        total = lottoResults.size();
    }

    @Override
    public String toString() {
        return Arrays.stream(LottoResult.values())
                .filter(it -> it != LottoResult.MISS)
                .map(this::getLottoResultString)
                .collect(Collectors.joining("\n"))
                + "\n" + getLottoEarningRateString();
    }

    private String getLottoResultString(LottoResult lottoResult) {
        return lottoResult.getString() + " - " + lottoHistogram.get(lottoResult) + "개";
    }

    private String getLottoEarningRateString() {
        return String.format("총 수익률은 %.2f입니다.", getLottoEarningRate());
    }

    private float getLottoEarningRate() {
        return Arrays.stream(LottoResult.values())
                .mapToInt(it -> it.getPrize() * lottoHistogram.get(it))
                .sum() / (float) (LottoTicket.LOTTO_TICKET_PRICE * total);
    }

    private void initStatisticsMap() {
        for (LottoResult lottoResult : LottoResult.values()) {
            lottoHistogram.put(lottoResult, 0);
        }
    }

    private void addCountOf(LottoResult lottoResult) {
        lottoHistogram.put(lottoResult, lottoHistogram.get(lottoResult)+1);
    }

}
