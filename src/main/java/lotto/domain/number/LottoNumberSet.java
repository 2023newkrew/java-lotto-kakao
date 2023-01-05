package lotto.domain.number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumberSet {

    private static final int LOTTO_NUMBER_SET_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoNumberSet(List<Integer> numberList) {
        this(new HashSet<>(numberList));
    }

    public LottoNumberSet(Set<Integer> numberSet) {
        validateNumbersSize(numberSet);
        lottoNumbers = numberSet.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
    }

    private void validateNumbersSize(Set<Integer> numbers) {
        if (numbers == null || numbers.size() != LOTTO_NUMBER_SET_SIZE) {
            throw new IllegalArgumentException(
                    String.format("%d개의 중복되지 않은 숫자가 필요합니다.", LOTTO_NUMBER_SET_SIZE));
        }
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public String getString() {
        return "[" + lottoNumbers.stream()
                .sorted()
                .map(LottoNumber::getString)
                .collect(Collectors.joining(", ")) + "]";
    }

    public int getAmountOfNumbersInNumbers(LottoNumberSet lottoNumbers) {
        return this.lottoNumbers.stream()
                .map(lottoNumbers::hasNumber)
                .mapToInt(it -> it.compareTo(false)).sum();
    }
}
