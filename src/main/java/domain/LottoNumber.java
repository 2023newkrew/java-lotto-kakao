package domain;

import java.util.Objects;

import static domain.LottoConstant.*;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        if(isLottoNumberOutOfRange(number)){
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_MIN_VALUE + "이상" + LOTTO_NUMBER_MAX_VALUE + "이하여야합니다.");
        }
        this.number = number;
    }

    private boolean isLottoNumberOutOfRange(int number) {
        return (number < LOTTO_NUMBER_MIN_VALUE || number > LOTTO_NUMBER_MAX_VALUE);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
