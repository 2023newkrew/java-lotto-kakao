package lotto.model.store;

import lotto.model.ticket.LottoTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

class LottoStoreTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("로또 가격이 없거나 0일 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        @MethodSource
        void should_throwException_when_priceIsNullOrZero(Money price) {
            Assertions.assertThatThrownBy(() -> LottoStore.create(price))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 가격은 0원일 수 없습니다.");
        }

        public List<Arguments> should_throwException_when_priceIsNullOrZero() {
            return List.of(Arguments.of(Money.ZERO));
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getPurchasableCount {

        @DisplayName("로또 가격이 1000원일 때 구입 가능한 로또 수량 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnCount_when_givenMoney(Money money, long expected) {
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            long count = store.getPurchasableCount(money);

            Assertions.assertThat(count).isEqualTo(expected);
        }

        public List<Arguments> should_returnCount_when_givenMoney() {
            return List.of(
                    Arguments.of(Money.ZERO, 0L),
                    Arguments.of(Money.valueOf(500L), 0L),
                    Arguments.of(Money.valueOf(1000L), 1L),
                    Arguments.of(Money.valueOf(3333L), 3L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getTotalPrice {

        @DisplayName("로또 가격이 1000원일 때 구입할 로또의 총 가격 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnTotalPrice_when_givenCount(long count, Money expected) {
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            Money money = store.getTotalPrice(count);

            Assertions.assertThat(money).isEqualTo(expected);
        }

        public List<Arguments> should_returnTotalPrice_when_givenCount() {
            return List.of(
                    Arguments.of(0L, Money.ZERO),
                    Arguments.of(1L, Money.valueOf(1000L)),
                    Arguments.of(2L, Money.valueOf(2000L)),
                    Arguments.of(99L, Money.valueOf(99000L))
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class buyAutomatically {

        @DisplayName("수량만큼 로또 반환")
        @ParameterizedTest
        @MethodSource
        void should_lottosCountIs_when_givenCount(long count) {
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            LottoTicket ticket = store.buyAutomatically(count);

            Assertions.assertThat(ticket.size()).isEqualTo(count);
        }

        public List<Arguments> should_lottosCountIs_when_givenCount() {
            return List.of(
                    Arguments.of(0L),
                    Arguments.of(1L),
                    Arguments.of(2L),
                    Arguments.of(99L)
            );
        }
    }
}