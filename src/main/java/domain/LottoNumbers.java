package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static domain.LottoConstant.LOTTO_NUMBERS_LENGTH;

public class LottoNumbers {
    public static final String INVALID_LOTTO_NUMBERS_LENGTH_EXCEPTION_MSG = String.format("로또의 길이는 %d 이어야 합니다.", LOTTO_NUMBERS_LENGTH);
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumber(List<LottoNumber> lottoNumbers) {
        if(isInValidLottoNumberLength(lottoNumbers)){
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_LENGTH_EXCEPTION_MSG);
        }
        if(hasDuplicatedLottoNumber(lottoNumbers)){
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicatedLottoNumber(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);
        return lottoNumberSet.size() != lottoNumbers.size();
    }

    private boolean isInValidLottoNumberLength(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_NUMBERS_LENGTH;
    }

    public boolean contains(LottoNumber lottoNumber){
        return lottoNumbers.contains(lottoNumber);
    }

    public int size(){
        return lottoNumbers.size();
    }

    public List<LottoNumber> findUnMatchLottoNumbers(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.stream()
                .filter(lottoNumber -> !lottoNumbers.contains(lottoNumber))
                .toList();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
