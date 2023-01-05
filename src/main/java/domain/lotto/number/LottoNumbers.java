package domain.lotto.number;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(final List<Integer> numbers) {
        validateNumberDuplication(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .boxed()
                .collect(Collectors.toList());
    }

    private void validateNumberDuplication(final List<Integer> lottoNumbers) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LOTTO_NUMBER_SIZE)
            throw new IllegalArgumentException("로또 번호는 중복없는 6자리 숫자여야 합니다.");
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
