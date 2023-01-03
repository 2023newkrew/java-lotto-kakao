package lotto;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoDispenser;
import lotto.domain.LottoTicketList;
import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.strategy.RandomNumberSelectStrategy;
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
        LottoDispenser lottoDispenser = new LottoDispenser(new RandomNumberSelectStrategy());
        Assertions.assertThat(lottoDispenser.getLottoTicket(price).getCount()).isEqualTo(number);
    }

    private static Stream<Arguments> getIssueLottoTicketByPriceData() {
        return Stream.of(
                Arguments.of(14000, 14),
                Arguments.of(500, 0),
                Arguments.of(0, 0)
        );
    }

    @DisplayName("숫자 생성 전략을 통한 숫자 생성")
    @ParameterizedTest
    @MethodSource("getIssueLottoTicketByNumberSelectStrategyData")
    public void issue_lotto_ticket_by_price(List<List<Integer>> randomNumbers, int price, String expected) {
        LottoDispenser lottoDispenser = new LottoDispenser(createNumberSelectStrategy(randomNumbers));
        LottoTicketList lottoTickets = lottoDispenser.getLottoTicket(price);
        Assertions.assertThat(lottoTickets.getLottoNumbersString()).isEqualTo(expected);
    }

    private static Stream<Arguments> getIssueLottoTicketByNumberSelectStrategyData() {
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
                )
        );
    }

    private NumberSelectStrategy createNumberSelectStrategy(List<List<Integer>> randomNumbers) {
        return new NumberSelectStrategy() {
            int index = 0;
            @Override
            public List<Integer> select() {
                return randomNumbers.get(index++);
            }
        };
    }
}
