package lotto.domain;

import static lotto.domain.LottoConstants.*;
import static lotto.domain.LottoNumber.*;
import static lotto.exception.ExceptionMessage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.ExceptionMessage;
import lotto.utils.ErrorMessageFormatter;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public static LottoNumbers makeLottoNumbers(List<Integer> integers) {
        return new LottoNumbers(integers.stream().map(LottoNumber::from).collect(Collectors.toList()));
    }

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers.size());
        validateDuplicateNumber(lottoNumbers);
    }


    private void validateSize(int size) {
        if (size != SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(SIZE_EXCEPTION_MESSAGE, size, "lottoNumbers size"));
        }
    }

    private void validateDuplicateNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(SIZE_EXCEPTION_MESSAGE, lottoNumbers,
                            "lottoNumbers duplicate"));

        }
    }

    public boolean contains(int input) {
        return this.lottoNumbers.contains(from(input));
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
