package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Integer LOTTO_NUMBER_LOWER_BOUNDARY = 1;
    private static final Integer LOTTO_NUMBER_UPPER_BOUNDARY = 45;

    private final Integer lottoNumber;

    public LottoNumber(Integer lottoNumber) {
        validateLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateLottoNumber(Integer lottoNumber) {
        if (lottoNumber < LOTTO_NUMBER_LOWER_BOUNDARY || lottoNumber > LOTTO_NUMBER_UPPER_BOUNDARY) {
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    public String getLottoNumberString() {
        return String.valueOf(lottoNumber);
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

    @Override
    public int compareTo(LottoNumber o) {
        return lottoNumber.compareTo(o.lottoNumber);
    }
}
