package lotto;

public class Ratio<T extends Number> extends Number {

    private final T numerator; // 분자
    private final T denominator; // 분모

    public Ratio(T numerator, T denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    public T getNumerator() {
        return numerator;
    }

    public T getDenominator() {
        return denominator;
    }
}
