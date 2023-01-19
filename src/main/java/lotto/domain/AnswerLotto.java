package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public PrizeGroupingMap getPrizeCountMap(List<Lotto> userLottos) {
        Map<LottoPrize, List<Lotto>> prizeCount = userLottos.stream().collect(Collectors.groupingBy(
                this::getLottoPrize
        ));

        return new PrizeGroupingMap(prizeCount);
    }

    private LottoPrize getLottoPrize(Lotto userLotto) {
        int matchNumberCount = this.answerLotto.countMatchNumber(userLotto);
        boolean hasBonusNumber = userLotto.containsLottoNumber(bonusNumber);

        return LottoCalculator.calculatePrize(matchNumberCount, hasBonusNumber);
    }
}
