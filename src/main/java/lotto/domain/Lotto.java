package lotto.domain;

import lotto.factory.LottoNumbersFactory;
import lotto.generatepolicy.DefaultGeneratePolicy;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(){
        this(LottoNumbersFactory.create(new DefaultGeneratePolicy()));
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoResult createLottoResult(LottoAnswer lottoAnswer) {
        return new DefaultLottoResult(getMatchCount(lottoAnswer.getLottoNumbers()), hasBonus(lottoAnswer.getBonus()));
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
