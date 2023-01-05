package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {

    @DisplayName("플레이어 생성시 돈은 0원 이상이여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1000, -9, -1})
    void playerMoney(int invalidMoney) {
        assertThatThrownBy(() -> new Player(invalidMoney));
    }

    @DisplayName("플레이어는 로또 티켓을 구매할 수 있다")
    @Test
    void buyLottoTicket() {
        //given
        Player player = new Player(5_500);
        LottoTicketSeller seller = new LottoTicketSeller();

        //when & then
        assertDoesNotThrow(() -> player.buyLottoTickets(seller));
    }

    @DisplayName("플레이어는 자신의 로또 티켓들의 당첨 결과를 받을 수 있다")
    @Test
    void calculateResults() {
        //given
        Player player = new Player(5_500);
        LottoTicketSeller seller = new LottoTicketSeller();

        //when
        player.buyLottoTickets(seller);
        PlayerLottoResult playerLottoResult = player.findResult(lottoTicket -> LottoResult.FIFTH_PLACE);

        //then
        assertThat(playerLottoResult.calculateProfitRate()).isEqualTo(5.0);
    }
}
