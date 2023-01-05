package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_SIZE;
import static lotto.domain.LottoNumber.from;
import static lotto.exception.ExceptionMessage.SIZE_EXCEPTION_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.utils.ErrorMessageFormatter;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public static LottoNumbers makeLottoNumbers(List<Integer> integers) {
        return new LottoNumbers(integers.stream().map(LottoNumber::from).collect(Collectors.toList()));
    }
    private LottoNumbers(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers.size());
        validateDuplicateNumber(lottoNumbers);
    }


    private void validateSize(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(SIZE_EXCEPTION_MESSAGE, size, "lottoNumbers size"));
        }
    }

    private void validateDuplicateNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(SIZE_EXCEPTION_MESSAGE, lottoNumbers,
                            "lottoNumbers duplicate"));

        }
    }

    public boolean contains(int input) {
        return this.lottoNumbers.contains(from(input));
    }

    public boolean contains(LottoNumber input) {
        return this.lottoNumbers.contains(input);
    }

    public int match(LottoNumbers other) {
        return (int) lottoNumbers.stream().filter(other::contains).count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
