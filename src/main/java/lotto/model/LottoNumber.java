package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

public class LottoNumber {
    private static final Integer LOTTO_NUMBER_LOWER_BOUNDARY = 1;
    private static final Integer LOTTO_NUMBER_UPPER_BOUNDARY = 45;

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(LOTTO_NUMBER_LOWER_BOUNDARY, LOTTO_NUMBER_UPPER_BOUNDARY)
                .forEach(number -> lottoNumbers.put(number, new LottoNumber(number)));
    }

    private final Integer lottoNumber;

    private LottoNumber(int lottoNumber) {
        validateLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(Integer lottoNumber) {
        return Optional.ofNullable(lottoNumbers.get(lottoNumber))
                .orElseThrow(() -> new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE));
    }

    public static LottoNumber from(String lottoNumberString) {
        int lottoNumber = Optional.of(Integer.parseInt(lottoNumberString))
                .orElseThrow(() -> new LottoException(ErrorCode.INVALID_INPUT_TYPE_NOT_INTEGER));
        return from(lottoNumber);
    }


    private void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < LOTTO_NUMBER_LOWER_BOUNDARY || lottoNumber > LOTTO_NUMBER_UPPER_BOUNDARY) {
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE);
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
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(lottoNumber, that.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
