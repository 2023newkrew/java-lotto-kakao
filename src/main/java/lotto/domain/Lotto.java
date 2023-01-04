package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotto {

    private final LottoNumbers lottoNumbers;
    private final SingleLottoNumber bonusNumber;

    public Lotto(LottoNumbers lottoNumbers, SingleLottoNumber bonusNumber) {
        if (lottoNumbers.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호가 정답 로또와 중복됩니다.");
        }

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

        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.isWon(matchNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(LottoPrize.NONE);
    }
}
