package lottov2.model.ticket;

public class SingleLottoNumber {

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 45;

    private final int value;

    public static SingleLottoNumber valueOf(int value) {
        if (isOutOfRange(value)) {
            String errorMessage = String.format("로또 번호는 %d ~ %d 사이의 숫자입니다.", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }

        return new SingleLottoNumber(value);
    }

    private SingleLottoNumber(int value) {
        this.value = value;
    }

    private static boolean isOutOfRange(int value) {
        return value < MIN_NUMBER || value > MAX_NUMBER;
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SingleLottoNumber)) {
            return false;
        }
        SingleLottoNumber that = (SingleLottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}

