package lotto.model;

import lotto.exception.InvalidLottoNumberValue;

import java.util.Objects;

public class LottoCount {
    private final int count;

    public LottoCount(Cash cash) {
        this((int)(cash.getCash() / LottoConstants.LOTTO_PRICE));
    }

    public LottoCount(int number) {
        if (number <= 0) {
            throw new InvalidLottoNumberValue();
        }

        this.count = number;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return count == ((LottoCount)o).getCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return count + "개";
    }
}
