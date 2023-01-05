package lotto;

import buyer.BuyerResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final int MATCH_FIVE = 5;

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
        boolean bonusMatch = false;
        int count = winningNumbers.compareWith(lotto);
        if (count == MATCH_FIVE) bonusMatch = isBonusMatch(lotto);
        return Rank.getRank(new LottoMatch(count, bonusMatch));
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
