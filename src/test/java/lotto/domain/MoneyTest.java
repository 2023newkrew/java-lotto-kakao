package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100, 0, -1000})
    void 금액이_양수가_아니면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }
}
