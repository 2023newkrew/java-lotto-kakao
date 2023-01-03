package lotto;

import java.util.Objects;

/**
 * @param <T> 비율로 사용할 타입 지정 {@link java.lang.Number} 타입이라면 모두 비율 측정의 용도로 사용 가능합니다.
 */
public class Ratio<T extends Number> extends Number {
    /**
     * 해당 오차 이내의 값은 동일한 값으로 취급합니다.
     * <p>
     * IEEE754 한계로 정확한 비교는 불가능하고 Bigint를 사용하지 않기에 불가피하게 이 개념을 도입합니다.
     * <p>
     * 기본 Machine Epsilon보다 둔감하게 설정했습니다. 이는 의도된 사항으로 약간의 오차를 허용하기 위함입니다.
     * <p>
     * 참조 : IEEE754 binary64 Machine Epsilon = 2^-52
     */
    private static final double SUPPRESS_EPSILON = 0.0000001d; // := 1^-7

    private final T numerator; // 분자
    private final T denominator; // 분모

    public Ratio(T numerator, T denominator) {
        if (Objects.isNull(numerator) || Objects.isNull(denominator)) {
            throw new IllegalArgumentException("Ratio는 null로는 생성 불가능합니다.");
        }

        if (numerator.doubleValue() == 0 && denominator.doubleValue() == 0) {
            throw new IllegalArgumentException("Ratio은 0:0 이 될 수 없습니다.");
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return "Ratio{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratio<?> ratio = (Ratio<?>) o;
        // 비교는 편의상 간단히 구현했습니다.
        return Math.abs(this.doubleValue() - ratio.doubleValue()) < SUPPRESS_EPSILON;
    }

    @Override
    public int intValue() {
        return ((int) doubleValue());
    }

    @Override
    public long longValue() {
        return ((long) doubleValue());
    }

    @Override
    public float floatValue() {
        return ((float) doubleValue());
    }

    @Override
    public double doubleValue() {
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }

    public T getNumerator() {
        return numerator;
    }

    public T getDenominator() {
        return denominator;
    }
}
