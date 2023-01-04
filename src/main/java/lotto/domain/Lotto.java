package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lotto {
    LottoNumbers lotto;

    public Lotto(int... args) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for(int arg:args)
            lottoNumbers.add(new LottoNumber(arg));
        lotto = new LottoNumbers(lottoNumbers);
    }

    public Lotto(LottoNumbers lottoNumbers){
        lotto = lottoNumbers;
    }

    public int compare(Lotto other){
        return lotto.matchCount(other.lotto);
    }

    public boolean hasBonus(LottoNumber other) {
        return lotto.contains(other);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
