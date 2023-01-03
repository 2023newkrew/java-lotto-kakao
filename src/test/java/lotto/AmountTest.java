package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class AmountTest {
    @ParameterizedTest
    @ValueSource(ints = {14000, 15300, 17800})
    @DisplayName("입력된 금액에 맞게 로또 티켓이 생성되어야 한다.")
    void lottoBuyTest(int cost) {
        LottoController lottoController = new LottoController(cost);
        assertThat(lottoController.getLottoTicketCount()).isEqualTo(cost / 1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    @DisplayName("1000원 미만의 금액이 들어온다면 예외를 발생한다.")
    void lottoLowerThan1000Test(int amount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTickets(amount));
    }
}