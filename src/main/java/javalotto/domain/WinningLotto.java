package javalotto.domain;

import javalotto.exception.winninglotto.BonusNumberDuplicateException;
import javalotto.exception.winninglotto.BonusNumberOutOfRangeException;

import java.util.Optional;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    private WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto lotto, int bonusNumber) {
        validBonusNumber(lotto, bonusNumber);

        return new WinningLotto(lotto, bonusNumber);
    }

    private static void validBonusNumber(Lotto lotto, int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new BonusNumberDuplicateException(bonusNumber);
        }

        if (Lotto.isInvalidRange(bonusNumber)) {
            throw new BonusNumberOutOfRangeException(bonusNumber);
        }
    }

    public Optional<Rank> getRank(Lotto lotto) {
        int matchCount = this.lotto.getMatchCount(lotto);
        boolean isBonusNumberMatch = lotto.contains(bonusNumber);

        return Rank.from(matchCount, isBonusNumberMatch);
    }
}
