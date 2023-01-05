package lottov2.model.ticket;

public class LottoNumber {

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 45;

    private final int value;

    public static LottoNumber valueOf(int value) {
        if (isOutOfRange(value)) {
            String errorMessage = String.format("로또 번호는 %d ~ %d 사이의 숫자입니다.", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }

        return new LottoNumber(value);
    }

    private LottoNumber(int value) {
        this.value = value;
    }

    private static boolean isOutOfRange(int value) {
        return value < MIN_NUMBER || value > MAX_NUMBER;
    }

    public int intValue() {
        return value;
    }
}

