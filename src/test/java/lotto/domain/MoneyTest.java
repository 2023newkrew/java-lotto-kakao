package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000, -500})
    void 돈은_0원_이상이다_실패(final int invalidAmount) {
        assertThatCode(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 0원 이상이여야 합니다.");
    }

    @Test
    void 돈에_돈을_더할_수_있다() {
        //given
        Money money = new Money(1_000);
        Money money2 = new Money(2_000);
        //when
        Money addResultMoney = money.add(money2);
        //then
        assertThat(addResultMoney).isEqualTo(new Money(3_000));
    }

    @Test
    void 돈을_돈으로_나눌_수_있다() {
        int amount1 = 3_000;
        int amount2 = 2_000;
        //given
        Money money = new Money(amount1);
        Money money2 = new Money(amount2);
        //when
        double divideResult = money.divideBy(money2);
        //then
        assertThat(divideResult).isEqualTo(amount1 / (double) amount2);
    }

    @Test
    void 돈에_숫자를_곱할_수_있다() {
        //given
        int amount = 1_000;
        int multiplyNumber = 3;
        Money money = new Money(amount);
        //when
        Money multiplyResultMoney = money.multiply(multiplyNumber);
        //then
        assertThat(multiplyResultMoney).isEqualTo(new Money(amount * multiplyNumber));
    }

    @Test
    void 돈에서_돈을_뺄_수_있다() {
        //given
        Money money1 = new Money(1_500);
        Money money2 = new Money(1_000);
        //when
        Money subtract = money1.subtract(money2);
        //then
        assertThat(subtract).isEqualTo(new Money(500));
    }
}