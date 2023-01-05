package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTicketSellerTest {

    @DisplayName("가격에 알맞는 갯수만큼 자동 로또 티켓을 구매할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 1000, 2000, 3500, 10000, 20000000})
    void sellAutoLottoTickets(int payMoney) {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();

        // when
        List<LottoTicket> lottoTicketsBought = seller.sellAutoLottoTickets(payMoney);

        // then
        assertThat(lottoTicketsBought.size()).isEqualTo(payMoney / 1000);
    }

    @DisplayName("번호 모음을 통해 수동으로 로또를 살 수 있다.")
    @Test
    void sellManualLottoTicket() {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );

        // when
        List<LottoTicket> lottoTicketsBought = seller.sellManualLottoTickets(manualLottoNumbers, 2000);

        // then
        assertThat(lottoTicketsBought.size()).isEqualTo(2);
    }

    @DisplayName("번호 모음을 통해 수동으로 로또를 살 때, payMoney는 수동으로 사려는 로또들의 가격 총합보다 같거나 커야한다.")
    @Test
    void sellManualLottoTicketPayMoneyValidation() {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        int payMoney = 1000;

        // when & then
        assertThatThrownBy(() -> seller.sellManualLottoTickets(manualLottoNumbers, payMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 0 미만이라면 로또 티켓을 구매할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -10, -300})
    void sellLottoTicketsInvalid(int payMoney) {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();

        // when & then
        assertThatThrownBy(() -> seller.sellAutoLottoTickets(payMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매한 로또 티켓들의 전체 가격을 알 수 있다.")
    @Test
    void calculateTotalPrice() {
        // given
        LottoTicketSeller seller = new LottoTicketSeller();
        List<LottoTicket> lottoTicketsBought = seller.sellAutoLottoTickets(3700);

        // when
        int lottoTicketsPrice = seller.calculateTotalPrice(lottoTicketsBought);

        // then
        assertThat(lottoTicketsPrice).isEqualTo(3000);
    }
}