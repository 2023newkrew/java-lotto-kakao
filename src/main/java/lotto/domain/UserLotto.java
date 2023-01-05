package lotto.domain;

public class UserLotto {

    private final LottoNumbers lottoNumbers;

    public UserLotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoPrize getLottoPrize(final WinningLotto winningLotto) {
        int matchNumberCount = lottoNumbers.countMatchNumber(winningLotto.lottoNumbers());
        boolean hasBonusNumber = lottoNumbers.containsLottoNumber(winningLotto.bonusNumber());

        return LottoRule.getPrize(matchNumberCount, hasBonusNumber);
    }

    @Override
    public String toString() {
        return this.lottoNumbers.toString();
    }
}
