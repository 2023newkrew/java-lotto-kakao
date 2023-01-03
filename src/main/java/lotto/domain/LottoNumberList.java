package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberList {
    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoNumberList(List<Integer> numbers) {
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

    public String getString() {
        return "[" + lottoNumbers.stream()
                .map(LottoNumber::getString)
                .collect(Collectors.joining(", ")) + "]";
    }

    public int getAmountOfNumbersInNumbers(LottoNumberList lottoNumberList) {
        return this.lottoNumbers.stream()
                .map(lottoNumberList::hasNumber)
                .mapToInt(it -> it.compareTo(false)).sum();
    }
}
