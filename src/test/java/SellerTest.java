import domain.LottoTicket;
import domain.Money;
import domain.Seller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SellerTest {
    @Test
    void 구매자로부터_돈을_받아_금액에_알맞는_로또티켓들을_돌려준다() {
        //given
        Seller seller = new Seller();
        Money payMoney = new Money(14000);
        Money payMoney2 = new Money(13500);

        //when
        List<LottoTicket> lottoTickets = seller.sellLottoTickets(payMoney);
        List<LottoTicket> lottoTickets2 = seller.sellLottoTickets(payMoney2);

        //then
        assertThat(lottoTickets.size()).isEqualTo(14);
        assertThat(lottoTickets2).hasSize(13);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 3_000, 5_000_000})
    void 구매자의_돈은_1000원_이상이여야한다(int validMoney) {
        //given
        Seller seller = new Seller();
        Money payMoney = new Money(validMoney);

        // when & then
        assertDoesNotThrow(() -> seller.sellLottoTickets(payMoney));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 500})
    void 구매자의_돈은_1000원_이상이여야한다_실패(int invalidMoney) {
        //given
        Seller seller = new Seller();
        Money payMoney = new Money(invalidMoney);

        // when & then
        assertThatThrownBy(() -> seller.sellLottoTickets(payMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 1,000원 이상을 지불해야 합니다.");
    }
}
