package lotto;

import java.util.Objects;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1부터 45 사이의 숫자를 입력해주세요.");
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public String getString() {
        return String.valueOf(number);
    }
}
