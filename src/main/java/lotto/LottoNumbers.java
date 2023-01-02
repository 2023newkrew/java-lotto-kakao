package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {
    List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoNumbers(List<Integer> numbers) {
        validateNumbersSize(numbers);
        for (Integer number : numbers) {
            LottoNumber lottoNumber = new LottoNumber(number);
            validateLottoNumber(lottoNumber);
            lottoNumbers.add(lottoNumber);
        }
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6) {
            throw new IllegalArgumentException("6개의 숫자 배열을 입력해주세요.");
        }
    }

    private void validateLottoNumber(LottoNumber lottoNumber) {
        if (hasNumber(lottoNumber)) {
            throw new IllegalArgumentException("중복되지 않는 숫자를 입력해주세요.");
        }
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
