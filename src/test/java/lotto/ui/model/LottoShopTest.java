package lotto.ui.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoShopTest {

    @DisplayName("티켓 구매 테스트")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "123456     | 123   | 456",
            "654321     | 654   | 321",
    })
    void moneyTest(long money, int expectedLottoCount, long expectedRemainMoney) {
        Player player = new Player();
        LottoShop lottoShop = new LottoShop();
        player.giveMoney(money);
        lottoShop.purchaseLotto(player);
        assertThat(player.getLottoTickets()
                .size()).isEqualTo(expectedLottoCount);
        assertThat(player.getOwnMoney()).isEqualTo(expectedRemainMoney);
    }
}
