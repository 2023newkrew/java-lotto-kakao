package domain.lotto.number;

public class LottoNumber {
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

    private int number;

    public int getNumber() {
        return this.number;
    }

    public LottoNumber (int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(final int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d 이상 %d 이하의 숫자여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            );
    }

    @Override
    public String toString() {
        return ""+number;
    }
}
