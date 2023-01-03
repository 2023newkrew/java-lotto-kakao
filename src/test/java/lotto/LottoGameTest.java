package lotto;

import java.util.stream.Stream;
import lotto.domain.LottoGame;
import lotto.domain.RandomNumberSelectStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoGameTest {

    LottoGame lottoGame = new LottoGame(new RandomNumberSelectStrategy());

    @DisplayName("로또 티켓 구매 (구매한 개수 리턴)")
    @ParameterizedTest
    @MethodSource("getBuyLottoTicketData")
    public void buy_lotto_ticket(int price, int number) {
        lottoGame.buy(price);
        Assertions.assertThat(lottoGame.getCountOfLottoTickets()).isEqualTo(number);
    }

    private static Stream<Arguments> getBuyLottoTicketData() {
        return Stream.of(
                Arguments.of(14000, 14),
                Arguments.of(500, 0),
                Arguments.of(0, 0)
        );
    }
}
