package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(10500);
    }

    @Test
    void 가진_돈과_로또의_가격으로_구매가능한_개수가_계산돼야_함() {
        assertThat(money.getPurchasableCount()).isEqualTo(10);
    }

    @Test
    void 구매하는데_사용된_금액이_계산돼야_함() {
        assertThat(money.getUsedMoney()).isEqualTo(10000);
    }
}