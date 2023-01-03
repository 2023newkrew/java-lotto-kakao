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

    private int getMatchCount(LottoNumbers otherLottoNumbers) {
        return lottoNumbers.getMatchCount(otherLottoNumbers);

    }

    private boolean hasBonus(int bonusNumber){
        return lottoNumbers.contains(bonusNumber);
    }
}
