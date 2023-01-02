package lotto.domain;

public class LottoAnswer {
    private final LottoNumbers lottoNumbers;
    private final int bonusNumber;

    public LottoAnswer(LottoNumbers lottoNumbers, int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public int getBonus() {
        return bonusNumber;
    }
}
