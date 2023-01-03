package lotto.domain;

public class Lotto {
    private final LottoNumbers lottoNumbers;
    private final SingleLottoNumber bonusNumber;

    public Lotto(LottoNumbers lottoNumbers, SingleLottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoPrize getLottoPrize(LottoNumbers userLotto) {
        int matchNumberCount = this.lottoNumbers.countMatchNumber(userLotto);
        boolean hasBonusNumber = userLotto.containsLottoNumber(bonusNumber);

        return LottoRule.getPrize(matchNumberCount, hasBonusNumber);
    }
}
