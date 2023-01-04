package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotto {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public Lotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public PrizeCountMap getPrizeCountMap(List<LottoNumbers> userLottos) {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();

        userLottos.forEach(userLotto -> {
            LottoPrize prize = getLottoPrize(userLotto);
            prizeCount.put(prize, prizeCount.getOrDefault(prize, 0) + 1);
        });

        return new PrizeCountMap(prizeCount);
    }

    private LottoPrize getLottoPrize(LottoNumbers userLotto) {
        int matchNumberCount = this.lottoNumbers.countMatchNumber(userLotto);
        boolean hasBonusNumber = userLotto.containsLottoNumber(bonusNumber);

        return LottoRule.getPrize(matchNumberCount, hasBonusNumber);
    }
}
