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

    public double getReturnRatio() throws ArithmeticException {
        if (trial == 0) {
            throw new ArithmeticException();
        }

        return (double)(totalPrize.getCash()) / (trial * LottoConstants.LOTTO_PRICE);
    }

    public int getLottoResultNum(LottoResult lottoResult) { return lottoResults.get(lottoResult); }
}
