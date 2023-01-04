package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 로또 번호가 중복되었습니다.");
        }
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
