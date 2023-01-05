package lotto;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(String winningNumbersAsString, int bonusNumber) {
        this.winningNumbers = new Lotto(winningNumbersAsString);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
        if (this.winningNumbers.containsLottoNumber(this.bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");
    }

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public Rank getRank(Lotto lotto) {
        int count = winningNumbers.compareWith(lotto);
        boolean bonusMatch = isBonusMatch(lotto);
        return Rank.getRank(count, bonusMatch);
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.containsLottoNumber(bonusNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WinningLotto)) return false;

        WinningLotto cp = (WinningLotto) obj;

        return this.winningNumbers.equals(cp.winningNumbers) && this.bonusNumber.equals(cp.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers, bonusNumber);
    }
}
