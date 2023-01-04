package lotto.domain;

import lotto.factory.LottoNumbersFactory;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(){
        this(LottoNumbersFactory.create());
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoResult createLottoResult(LottoAnswer lottoAnswer) {
        return new LottoResult(getMatchCount(lottoAnswer.getLottoNumbers()), hasBonus(lottoAnswer.getBonus()));
    }
    private int getMatchCount(LottoNumbers otherLottoNumbers) {
        return lottoNumbers.getMatchCount(otherLottoNumbers);
    }

    private boolean hasBonus(int bonusNumber){
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
