package lotto.domain;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(){
        this(LottoNumbersFactory.create());
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }


}
