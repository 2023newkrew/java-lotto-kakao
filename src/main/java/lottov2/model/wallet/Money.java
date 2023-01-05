package lottov2.model.wallet;

import java.math.BigDecimal;

public class Money {

    private final long value;

    public static Money valueOf(long value) {
        if (value < 0L) {
            throw new IllegalArgumentException("돈은 0원 이상입니다.");
        }

        return new Money(value);
    }

    private Money(long value) {
        this.value = value;
    }

    public long longValue(){
        return value;
    }
}
