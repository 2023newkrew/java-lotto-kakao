package domain.lotto.number;

import exception.LottoNumberOutOfRangeException;

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
            throw new LottoNumberOutOfRangeException();
    }

    @Override
    public String toString() {
        return ""+number;
    }
}
