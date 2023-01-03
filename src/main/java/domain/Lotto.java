package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static domain.LottoConstant.LOTTO_LENGTH;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumber(List<LottoNumber> lottoNumbers) {
        if(isInValidLottoNumberLength(lottoNumbers)){
            throw new IllegalArgumentException("로또의 길이는 " + LOTTO_LENGTH + "이어야 합니다.");
        }
        if(hasDuplicatedLottoNumber(lottoNumbers)){
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicatedLottoNumber(List<LottoNumber> lottoNumbers) {
        Set<Integer> lottoNumberSet = new HashSet<>();
        for(LottoNumber lottoNumber : lottoNumbers){
            lottoNumberSet.add(lottoNumber.getNumber());
        }
        return lottoNumberSet.size() != lottoNumbers.size();
    }

    private boolean isInValidLottoNumberLength(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_LENGTH;
    }

    public int match(Lotto that, LottoNumber bonusNumber){
        List<LottoNumber> nonMatchLottoNumbers = new ArrayList<>();

        return 1;
    }
}
