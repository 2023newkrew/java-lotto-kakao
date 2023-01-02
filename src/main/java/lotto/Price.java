package lotto;

import java.util.Objects;

public class Price {
    private int value;
    public Price(int value) {
        this.value = value;
    }

    public void add(int addValue) {
        if (addValue < 0){
            throw new IllegalArgumentException("더하는 금액은 0 미만이 될 수 없습니다.");
        }
        this.value += addValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
