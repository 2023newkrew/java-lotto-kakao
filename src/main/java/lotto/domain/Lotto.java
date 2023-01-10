package lotto.domain;

import java.util.ArrayList;
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

    public Lotto(List<Integer> numbers){
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for(int number:numbers)
            lottoNumbers.add(new LottoNumber(number));
        lotto = new LottoNumbers(lottoNumbers);
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

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Lotto))
            return false;
        return lotto.matchCount(((Lotto) obj).lotto) == LottoNumbers.SIZE;
    }
}
