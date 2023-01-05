package domain.lotto.number;

import domain.lotto.number.generator.NumbersGeneratable;
import exception.InvalidLottoNumbersException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> numbers;

    private LottoNumbers(final List<Integer> numbers) {
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

    public static LottoNumbers create(NumbersGeneratable numbersGeneratable) {
        return new LottoNumbers(numbersGeneratable.generate());
    }

    private void validateNumberDuplication(final List<Integer> lottoNumbers) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LOTTO_NUMBER_SIZE)
            throw new InvalidLottoNumbersException();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
