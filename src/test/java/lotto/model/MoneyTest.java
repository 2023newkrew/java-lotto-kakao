package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(10500);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 금액이_유효하지_않은_경우_예외가_발생해야_함(int money) {
        assertThatCode(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 가진_돈과_로또의_가격으로_구매가능한_개수가_계산돼야_함() {
        assertThat(money.getPurchasableCount()).isEqualTo(10);
    }

    @Test
    void 구매하는데_사용된_금액이_계산돼야_함() {
        money = money.buyLottoTicketsAsManyAs(10);
        assertThat(money.getUsedMoney()).isEqualTo(10000);
    }

    @Test
    void 수동으로_티켓을_구매한_뒤의_객체가_달라야_함() {
        Money money = this.money.buyLottoTicketsAsManyAs(3);
        assertThat(money).isNotSameAs(this.money);
    }

    @Test
    void 수동으로_티켓을_구매한_뒤_사용된_금액이_바뀌어야_함() {
        money = money.buyLottoTicketsAsManyAs(3);
        assertThat(money.getUsedMoney()).isEqualTo(3000);
    }
}