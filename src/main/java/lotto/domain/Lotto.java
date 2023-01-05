package lotto.domain;

import static lotto.LottoConfig.LOTTO_SIZE;
import static lotto.constant.MessageConstant.INVALID_DUPLICATED_LOTTO_NUMBER;
import static lotto.constant.MessageConstant.INVALID_LOTTO_SIZE;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateLotto(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void validateLotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicated(numbers);
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE);
        }
    }

    private void validateDuplicated(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_LOTTO_NUMBER);
        }
    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
