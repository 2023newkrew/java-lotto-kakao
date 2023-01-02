package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;
import static lotto.exception.ExceptionMessages.INVALID_COUNT_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.NOT_UNIQUE_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.OUT_OF_BOUNDS_EXCEPTION_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumbers {

    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        lottoNumbers = new ArrayList<>(lottoNumbers);
        validate(lottoNumbers);
        lottoNumbers.sort(Integer::compare);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<Integer> lottoNumbers) {
        validateCount(lottoNumbers);
        validateAllRange(lottoNumbers);
        validateUniqueness(lottoNumbers);
    }

    private void validateUniqueness(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(NOT_UNIQUE_EXCEPTION_MESSAGE);
        }
    }

    private void validateCount(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private void validateAllRange(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(this::validateSingleRange);
    }

    private void validateSingleRange(int number) {
        if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
