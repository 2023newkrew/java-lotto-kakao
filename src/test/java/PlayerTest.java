import domain.Money;
import domain.Player;
import domain.Seller;
import org.junit.jupiter.api.Test;

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
}
