package lotto;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 45;

    private final int number;

    public LottoNumber(int number){
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            String errorMessage = String.format("로또 번호는 %d ~ %d 사이의 숫자입니다.", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static boolean isOutOfRange(int number) {
        return number < MIN_NUMBER || number > MAX_NUMBER;
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
}
