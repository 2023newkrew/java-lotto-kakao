package lotto;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoGame;
import lotto.domain.LottoSetting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoGameTest {

    @DisplayName("로또 티켓 구매 (구매한 개수 리턴)")
    @ParameterizedTest
    @MethodSource("getBuyLottoTicketData")
    public void buyLottoTicket(int money, int number) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyRandom(money);
        Assertions.assertThat(lottoGame.getCountOfLottoTickets()).isEqualTo(number);
    }

    private static Stream<Arguments> getBuyLottoTicketData() {
        return Stream.of(
                Arguments.of(14000, 14),
                Arguments.of(500, 0),
                Arguments.of(0, 0)
        );
    }

    @DisplayName("수동으로 티켓 발급")
    @ParameterizedTest
    @MethodSource("getIssueLottoTicketManuallyData")
    public void issueLottoTicketManually(List<List<Integer>> numbers, int money, String expected) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyManually(money, numbers);
        Assertions.assertThat(lottoGame.getLottoTicketsString()).isEqualTo(expected);
    }

    private static Stream<Arguments> getIssueLottoTicketManuallyData() {
        return Stream.of(
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6)
                        ),
                        1000, "[1, 2, 3, 4, 5, 6]"),
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(5, 6, 20, 23, 40, 41)
                        ),
                        2500, "[1, 2, 3, 4, 5, 6]\n[5, 6, 20, 23, 40, 41]"
                ),
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(5, 6, 20, 23, 40, 41)
                        ),
                        1500, "[1, 2, 3, 4, 5, 6]"
                )
        );
    }

    @DisplayName("게임에서 남은 금액 가져오기")
    @ParameterizedTest
    @MethodSource("getReceiveLeftoverMoneyData")
    public void receiveLeftoverMoney(int money, int number) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyRandom(money);
        Assertions.assertThat(lottoGame.receiveLeftoverMoney()).isEqualTo(number);
    }

    private static Stream<Arguments> getReceiveLeftoverMoneyData() {
        return Stream.of(
                Arguments.of(14000, 0),
                Arguments.of(500, 500),
                Arguments.of(1350, 350)
        );
    }
}
