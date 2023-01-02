package lotto;

import static lotto.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;

import java.util.List;

public class LottoNumbers {

    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<Integer> lottoNumbers) {
        validateCount(lottoNumbers);
        validateAllRange(lottoNumbers);
        validateUniqueness(lottoNumbers);
    }

    private void validateUniqueness(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCount(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void validateAllRange(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(this::validateSingleRange);
    }

    private void validateSingleRange(int number) {
        if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException();
        }
    }

}
