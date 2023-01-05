package lotto.domain;

import static lotto.constant.ExceptionMessages.INVALID_DUPLICATED_LOTTO_NUMBER;
import static lotto.constant.ExceptionMessages.INVALID_LOTTO_SIZE;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_POOL;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<Integer> numbers) {
        validateLotto(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public static Lotto generateLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static Lotto generateRandomLotto() {
        Collections.shuffle(LOTTO_NUMBER_POOL);
        return new Lotto(LOTTO_NUMBER_POOL.subList(0, 6));
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

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
