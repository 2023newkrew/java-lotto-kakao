package lotto.model.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class createByRandom {

        @DisplayName("count 수 만큼 lotto를 생성해 반환")
        @ParameterizedTest
        @ValueSource(longs = {0L, 1L, 3L, 9L, 99L})
        void should_lottoCountIs_when_givenCount(long count) {
            LottoTicket ticket = LottoTicket.createByRandom(count);

            Assertions.assertThat(ticket.count()).isEqualTo(count);
        }

        @DisplayName("count가 0보다 작을 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(longs = {-1L, -3L, -5L})
        void should_throwException_when_countUnderZero(long count) {
            Assertions.assertThatThrownBy(() -> LottoTicket.createByRandom(count))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("생성할 로또의 수량이 0보다 작습니다.");
        }
    }
}