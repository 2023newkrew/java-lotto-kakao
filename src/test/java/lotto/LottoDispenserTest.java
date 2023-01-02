package lotto;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoDispenserTest {

    @DisplayName("금액에 따른 로또 티켓 발급")
    @ParameterizedTest
    @MethodSource("getIssueLottoTicketByPriceData")
    public void issue_lotto_ticket_by_price(int price, int number) {
        LottoDispenser lottoDispenser = new LottoDispenser();
        Assertions.assertThat(lottoDispenser.getLottoTicket(price).size()).isEqualTo(number);
    }

    private static Stream<Arguments> getIssueLottoTicketByPriceData() {
        return Stream.of(
                Arguments.of(14000, 14),
                Arguments.of(500, 0),
                Arguments.of(0, 0)
        );
    }
}
