package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerLotto {

    private final Lotto answerLotto;
    private final SingleLottoNumber bonusNumber;

    public AnswerLotto(Lotto answerLotto, SingleLottoNumber bonusNumber) {
        if (answerLotto.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호가 정답 로또와 중복됩니다.");
        }

        this.answerLotto = answerLotto;
        this.bonusNumber = bonusNumber;
    }

    public PrizeCountMap getPrizeCountMap(List<Lotto> userLottos) {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();

        userLottos.forEach(userLotto -> {
            LottoPrize prize = getLottoPrize(userLotto);
            prizeCount.put(prize, prizeCount.getOrDefault(prize, 0) + 1);
        });

        return new PrizeCountMap(prizeCount);
    }

    private LottoPrize getLottoPrize(Lotto userLotto) {
        int matchNumberCount = this.answerLotto.countMatchNumber(userLotto);
        boolean hasBonusNumber = userLotto.containsLottoNumber(bonusNumber);

        return LottoCalculator.calculatePrize(matchNumberCount, hasBonusNumber);
    }
}
