package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {

    @DisplayName("플레이어 생성시 돈은 0원 이상이여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1000, -9, -1})
    void playerMoney(int invalidMoney) {
        assertThatThrownBy(() -> new Player(invalidMoney));
    }

    @DisplayName("플레이어는 자동 로또 티켓을 구매할 수 있다")
    @Test
    void buyAutoLottoTicket() {
        //given
        Player player = new Player(5_500);
        LottoTicketSeller seller = new LottoTicketSeller();

        //when & then
        assertDoesNotThrow(() -> player.buyAutoLottoTickets(seller));
    }

    @DisplayName("플레이어는 수동으로 로또 티켓을 구매할 수 있다.")
    @Test
    void buyManualLottoTicket() {
        //given
        Player player = new Player(5_500);
        LottoTicketSeller seller = new LottoTicketSeller();
        List<List<Integer>> lottoNumbers = List.of(List.of(1, 2, 3, 4, 5, 6));

        //when & then
        assertDoesNotThrow(() -> player.buyManualLottoTickets(seller, lottoNumbers));
    }

    @DisplayName("플레이어는 자신의 로또 티켓들의 당첨 결과를 받을 수 있다")
    @Test
    void calculateResults() {
        //given
        Player player = new Player(5_500);
        LottoTicketSeller seller = new LottoTicketSeller();
        List<List<Integer>> lottoNumbers = List.of(List.of(1, 2, 3, 4, 5, 6));

        //when
        player.buyManualLottoTickets(seller, lottoNumbers);
        player.buyAutoLottoTickets(seller);
        PlayerLottoResult playerLottoResult = player.findResult(lottoTicket -> LottoResult.FIFTH_PLACE);

        //then
        assertThat(playerLottoResult.calculateProfitRate()).isEqualTo(5.0);
    }
}
