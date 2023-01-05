package lotto.model;

import lotto.model.errors.LottoOutOfRangeException;

import java.util.Comparator;
import java.util.Objects;

public class Money implements Comparator<Money>, Comparable<Money> {

    private Integer value;

    public Money() {
        this(0);
    }

    public Money(Integer value) {
        this.value = value;
        validateValue();
    }

    private void validateValue() {
        if (value < 0) {
            throw new LottoOutOfRangeException("돈은 음수일 수 없습니다.");
        }
    }

    public Integer getValue() {
        return value;
    }

    public Money add(Integer expense) {
        value += expense;
        return this;
    }

    public Money add(Money expense) {
        return add(expense.value);
    }

    public Money spend(Integer expense) {
        value -= expense;
        validateValue();
        return this;
    }

    public Money spend(Money expense) {
        return spend(expense.value);
    }

    @Override
    public int compare(Money o1, Money o2) {
        return o1.value - o2.value;
    }

    @Override
    public int compareTo(Money o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
