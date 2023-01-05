package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    @Test
    void 플레이어는_로또티켓을_구매할_수_있다() {
        //given
        Player player = new Player(new Money(5_500));
        Seller seller = new Seller();
        //when, then
        assertDoesNotThrow(() -> player.buyLottoTickets(seller));
    }

    @Test
    void 플레이어는_자신의_로또티켓들의_당첨결과를_받을_수_있다() {
        //given
        Player player = new Player(new Money(5_500));
        Seller seller = new Seller();
        //when
        player.buyLottoTickets(seller);
        player.initPlayerLottoResult();
        PlayerLottoResult playerLottoResult = player.findResult(lottoTicket -> LottoResult.THREE_MATCH);
        //then
        assertThat(playerLottoResult.calculateProfitRate()).isEqualTo(400.00);
    }

    @Test
    void 플레이어는_수동_로또티켓을_구매할_수_있다() {
        //given
        Player player = new Player(new Money(5_500));
        Seller seller = new Seller();
        Set<LottoBall> lottoBalls = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(num -> new LottoBall(num)).collect(Collectors.toSet());
        List<LottoTicket> manualLottoTickets = List.of(new LottoTicket(lottoBalls), new LottoTicket(lottoBalls));
        //when, then
        assertDoesNotThrow(() -> player.buyManualLottoTickets(seller, manualLottoTickets));
    }

}
