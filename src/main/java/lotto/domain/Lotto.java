package lotto.domain;

public class Lotto {
    private final LottoNumbers lottoNumbers;
    private final SingleLottoNumber bonusNumber;

    public Lotto(LottoNumbers lottoNumbers, SingleLottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoPrize getPrize(LottoNumbers userLotto) {
        int matchNumberCount = this.lottoNumbers.countMatchNumber(userLotto);
        boolean hasBonusNumber = userLotto.containsLottoNumber(bonusNumber);

        if (matchNumberCount == 6) {
            return LottoPrize.FIRST_PRIZE;
        }
        if (matchNumberCount == 5 && hasBonusNumber) {
            return LottoPrize.SECOND_PRIZE;
        }
        if (matchNumberCount == 5) {
            return LottoPrize.THIRD_PRIZE;
        }
        if (matchNumberCount == 4) {
            return LottoPrize.FOURTH_PRIZE;
        }
        if (matchNumberCount == 3) {
            return LottoPrize.FIFTH_PRIZE;
        }
        return LottoPrize.NONE;
    }
}
