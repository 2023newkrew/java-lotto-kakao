import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    @Test
    void 플레이어는_로또티켓을_구매할_수_있다() {
        //given
        Player player = new Player(new Money(5_500));
        Seller seller = new Seller();
        //when
        assertDoesNotThrow(() -> player.buyLottoTickets(seller));
    }

    @Test
    void 플레이어는_자신의_로또티켓들의_당첨결과를_받을_수_있다() {
        //given
        Player player = new Player(new Money(5_500));
        Seller seller = new Seller();
        //when
        player.buyLottoTickets(seller);
        PlayerLottoResult playerLottoResult = player.findResult(lottoTicket -> LottoResult.THREE_MATCH);
        //then
        assertThat(playerLottoResult.calculateProfitRate()).isEqualTo(5.0);
    }

}
