package domain;

import common.state.Result;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Result getResult(Lotto lotto) {
        return Result.of(winningLotto.getMatchCount(lotto), isBonus(lotto));
    }

    public boolean isBonus(Lotto lotto) {
        return lotto.containsNumber(bonusNumber);
    }

}
