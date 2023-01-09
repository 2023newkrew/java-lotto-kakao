package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoDispenser;
import lotto.domain.LottoTicketsManager;
import lotto.strategy.AutoNumberSelectStrategy;
import lotto.strategy.RandomAutoNumberSelectStrategy;
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
        LottoDispenser lottoDispenser = new LottoDispenser(new RandomAutoNumberSelectStrategy());
        Assertions.assertThat(lottoDispenser.getLottoTickets(price, Collections.emptyList()).getSize()).isEqualTo(number);
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
        LottoTicketsManager lottoTicketsManager = lottoDispenser.getLottoTickets(price, Collections.emptyList());
        Assertions.assertThat(lottoTicketsManager.getLottoNumbersString()).isEqualTo(expected);
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

    private AutoNumberSelectStrategy createNumberSelectStrategy(List<List<Integer>> randomNumbers) {
        return new AutoNumberSelectStrategy() {
            int index = 0;
            @Override
            public List<Integer> selectNumbers() {
                return randomNumbers.get(index++);
            }
        };
    }
}
