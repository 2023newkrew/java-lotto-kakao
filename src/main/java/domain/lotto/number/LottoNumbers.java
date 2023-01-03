package domain.lotto.number;

import domain.lotto.LottoMetaData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validateNumberDuplication(numbers);
        numbers.forEach(this::validateNumberRange);
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateNumberDuplication(final List<Integer> lottoNumbers) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoMetaData.LOTTO_NUMBER_SIZE)
            throw new IllegalArgumentException("로또 번호는 중복없는 6자리 숫자여야 합니다.");
    }

    private void validateNumberRange(final Integer number) {
        if (number < LottoMetaData.MIN_LOTTO_NUMBER || number > LottoMetaData.MAX_LOTTO_NUMBER)
            throw new IllegalArgumentException("번호는 1 이상 45 이하의 숫자여야 합니다.");
    }

}
