package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTicketSellerTest {

    @DisplayName("가격에 알맞는 갯수만큼 로또 티켓을 구매할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3500, 10000, 20000000})
    void sellLottoTickets(int payMoney) {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();

        // when
        List<LottoTicket> lottoTicketsBought = seller.sellLottoTickets(payMoney);

        // then
        assertThat(lottoTicketsBought.size()).isEqualTo(payMoney / 1000);
    }

    @DisplayName("가격이 1000 미만이라면 로또 티켓을 구매할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, 0, 300})
    void sellLottoTicketsInvalid(int payMoney) {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();

        // when & then
        assertThatThrownBy(() -> seller.sellLottoTickets(payMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매한 로또 티켓들의 전체 가격을 알 수 있다.")
    @Test
    void calculateTotalPrice() {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();
        List<LottoTicket> lottoTicketsBought = seller.sellLottoTickets(3700);

        // when
        int lottoTicketsPrice = seller.calculateTotalPrice(lottoTicketsBought);

        // then
        assertThat(lottoTicketsPrice).isEqualTo(3000);
    }
}