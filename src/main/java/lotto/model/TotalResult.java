package lotto.model;

import java.util.*;
import java.util.stream.Collectors;

public class TotalResult {
    private final Map<LottoResult, Integer> lottoResults = new HashMap<>();
    private Cash totalPrize = new Cash(0);
    private int trial = 0;

    public TotalResult() {
        for (WinningCondition v : WinningCondition.values()) {
            lottoResults.put(v.getLottoResult(), 0);
        }
    }

    public void addResult(LottoResult lottoResult) {
        trial++;

        addPrize(lottoResult);

        if (lottoResults.containsKey(lottoResult)) {
            lottoResults.put(lottoResult, lottoResults.get(lottoResult) + 1);
        }
    }

    private void addPrize(LottoResult lottoResult) {
        List<Cash> prizes = Arrays.stream(WinningCondition.values())
                .map(v -> v.getPrizeIfMatch(lottoResult))
                .filter(v -> v.compareTo(0L) > 0)
                .collect(Collectors.toList());

        if (prizes.size() != 0) {
            totalPrize = totalPrize.plus(prizes.get(0));
        }
    }

    private String surplusRatioString() {
        double surplusRatio = getSurplusRatio();

        return String.format("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                surplusRatio, (surplusRatio > 1 ? "이득이" : "손해"));
    }

    public double getSurplusRatio() throws ArithmeticException {
        if (trial == 0) {
            throw new ArithmeticException();
        }

        return (double)(totalPrize.getCash()) / (trial * LottoConstants.LOTTO_PRICE);
    }

    private String statistics() {
        String[] strings = new String[WinningCondition.values().length];

        for (WinningCondition con : WinningCondition.values()) {
            strings[con.getOrder()] = con + "- " +
                    lottoResults.get(con.getLottoResult()) + "개";
        }

        return String.join("\n", strings) + "\n";
    }

    @Override
    public String toString() {
        String startString = "당첨 통계\n-----------\n";

        return startString + statistics() + surplusRatioString();
    }
}
