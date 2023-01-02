package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {
    List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoNumbers(List<Integer> numbers) {
        for (Integer number : numbers) {
            LottoNumber lottoNumber = new LottoNumber(number);
            validateLottoNumber(lottoNumber);
            lottoNumbers.add(lottoNumber);
        }
    }

    private void validateLottoNumber(LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("중복되지 않는 숫자를 입력해주세요.");
        }
    }
}
