package lotto.model;

import lotto.exception.InvalidCashValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CashTest {
    @ParameterizedTest
    @ValueSource(longs = {-1, -1000, -4000, 0, 100, 500, 999})
    void 구매금액이_로또_1장_가격_미만인_경우_예외(long value) {
        assertThatThrownBy(() -> {
            Cash cash = new BuyCash(value);
        }).isInstanceOf(InvalidCashValue.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {1000, 5000, 40000000})
    void 구매금액이_정상_금액인_경우_정상실행(long value) {
        assertThatCode(() -> {
            Cash cash = new BuyCash(value);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, -1000, -4000})
    void 상금이_음수인_경우_예외(long value) {
        assertThatThrownBy(() -> {
            Cash cash = new Prize(value);
        }).isInstanceOf(InvalidCashValue.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 100, 1000, 5000, 40000000})
    void 상금이_정상_금액인_경우_정상실행(long value) {
        assertThatCode(() -> {
            Cash cash = new Prize(value);
        }).doesNotThrowAnyException();
    }

    @Test
    void 금액끼리_더할_수_있어야_한다() {
        long val1 = 1000;
        long val2 = 2000;

        Cash buyCash1 = new BuyCash(val1);
        Cash buyCash2 = new BuyCash(val2);
        Cash buyCash3 = buyCash1.plus(buyCash2);
        Cash buyCash4 = buyCash3.plus(val2);

        Cash prize1 = new Prize(val1);
        Cash prize2 = new Prize(val2);
        Cash prize3 = prize1.plus(prize2);
        Cash prize4 = prize3.plus(val2);

        Assertions.assertThat(buyCash3.getCash()).isEqualTo(3000);
        Assertions.assertThat(buyCash4.getCash()).isEqualTo(5000);

        Assertions.assertThat(prize3.getCash()).isEqualTo(3000);
        Assertions.assertThat(prize4.getCash()).isEqualTo(5000);
    }
}
