package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class LottoTicketPurchaseAmountTest {

    @DisplayName("Cost와 로또 구매 개수를 넘겨 받아서 로또 구매 개수 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    void create(int purchaseAmount) {
        Cost cost = new Cost(10_000);
        assertThatNoException()
                .isThrownBy(() -> new LottoTicketPurchaseAmount(purchaseAmount, cost));
    }

    @DisplayName("로또 구매 개수가 음수이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void create_throw_negative(int purchaseAmount) {
        Cost cost = new Cost(10_000);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicketPurchaseAmount(purchaseAmount, cost));
    }

    @DisplayName("Cost로 구매 개수만큼의 로또를 구매할 수 없으면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 500, 1000, 5000, 7900, 9999})
    void create_throw_cost(int money) {
        int purchaseCount = 10;
        Cost cost = new Cost(money);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicketPurchaseAmount(purchaseCount, cost));
    }
}
