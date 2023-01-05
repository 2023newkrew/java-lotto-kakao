package lotto.model.ticket;

public class LottoNumber {
    public static final int NUMBER_RANGE = 45;
    private final int number;

    public LottoNumber(int number) {
        if (!this.isValidNumber(number)) {
            throw new IllegalArgumentException("로또 번호는 1에서 45 사이의 정수여야 합니다.");
        }
        this.number = number;
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= LottoNumber.NUMBER_RANGE;
    }

    public int getNumber() {
        return this.number;
    }
}
