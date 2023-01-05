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
        lottoGame.buyRandomly(money);
        Assertions.assertThat(lottoGame.getRandomTicketCount()).isEqualTo(number);
    }

    private static Stream<Arguments> getBuyLottoTicketData() {
        return Stream.of(
                Arguments.of(14000, 14),
                Arguments.of(500, 0),
                Arguments.of(0, 0)
        );
    }

    @DisplayName("수동으로 티켓 구매")
    @ParameterizedTest
    @MethodSource("getBuyLottoTicketManuallyData")
    public void buyLottoTicketManually(List<List<Integer>> numbers, int money, String expected) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyManually(money, numbers);
        Assertions.assertThat(lottoGame.getLottoTicketsString()).isEqualTo(expected);
    }

    private static Stream<Arguments> getBuyLottoTicketManuallyData() {
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

    @DisplayName("수동으로 티켓 구매 실패")
    @ParameterizedTest
    @MethodSource("getFailToBuyLottoTicketManuallyData")
    public void failToBuyLottoTicketManually(List<List<Integer>> numbers, int money) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        Assertions.assertThatThrownBy(() -> lottoGame.buyManually(money, numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getFailToBuyLottoTicketManuallyData() {
        return Stream.of(
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4)
                        ),
                        1000,
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 4, 6)
                        ),
                        2500
                ),
                Arguments.of(List.of(
                                List.of(-1, 2, 3, 4, 5, 6)
                        ),
                        1500
                ),
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6, 7)
                        ),
                        1500
                )));
    }

    @DisplayName("발급 수량 확인")
    @ParameterizedTest
    @MethodSource("getCheckCountData")
    public void checkCount(List<List<Integer>> numbers, int money, int manualExpected, int randomExpected) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyManually(money, numbers);
        lottoGame.buyRandomly(lottoGame.receiveLeftoverMoney());
        Assertions.assertThat(lottoGame.getManualTicketCount()).isEqualTo(manualExpected);
        Assertions.assertThat(lottoGame.getRandomTicketCount()).isEqualTo(randomExpected);
    }

    private static Stream<Arguments> getCheckCountData() {
        return Stream.of(
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6)
                        ),
                        1000, 1, 0),
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(5, 6, 20, 23, 40, 41)
                        ),
                        5000, 2, 3),
                Arguments.of(List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(5, 6, 20, 23, 40, 41)
                        ),
                        3500, 2, 1)
        );
    }

    @DisplayName("게임에서 남은 금액 가져오기")
    @ParameterizedTest
    @MethodSource("getReceiveLeftoverMoneyData")
    public void receiveLeftoverMoney(int money, int number) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);
        lottoGame.buyRandomly(money);
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
