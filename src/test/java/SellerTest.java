import domain.LottoBall;
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
        int payMoney = 14000;
        int payMoney2 = 13500;

        //when
        List<List<LottoBall>> lottoTickets = seller.sellLottoTickets(payMoney);
        List<List<LottoBall>> lottoTickets2 = seller.sellLottoTickets(payMoney2);

        //then
        assertThat(lottoTickets.size()).isEqualTo(14);
        assertThat(lottoTickets2).hasSize(13);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 3_000, 5_000_000})
    void 구매자의_돈은_1000원_이상이여야한다(int validMoney) {
        //given
        Seller seller = new Seller();

        // when & then
        assertDoesNotThrow(() -> seller.sellLottoTickets(validMoney));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1_000, 0, 500})
    void 구매자의_돈은_1000원_이상이여야한다_실패(int invalidMoney) {
        //given
        Seller seller = new Seller();

        // when & then
        assertThatThrownBy(() -> seller.sellLottoTickets(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 1,000원 이상을 지불해야 합니다.");
    }
}
