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
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class LottoMachineTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("로또 가격이 없거나 0일 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        @MethodSource
        void should_throwException_when_priceIsNullOrZero(Money price) {
            Assertions.assertThatThrownBy(() -> LottoMachine.create(price))
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

        @DisplayName("구입 가능한 로또 갯수 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnCount_when_givenMoney(Money money, long count) {
            Money price = Money.valueOf(1000L);
            LottoMachine lottoMachine = LottoMachine.create(price);

            long actual = lottoMachine.getPurchasableCount(money);

            Assertions.assertThat(actual).isEqualTo(count);
        }

        public List<Arguments> should_returnCount_when_givenMoney() {
            return List.of(
                    Arguments.of(null, 0L),
                    Arguments.of(Money.ZERO, 0L),
                    Arguments.of(Money.valueOf(1000L), 1L),
                    Arguments.of(Money.valueOf(1500L), 1L),
                    Arguments.of(Money.valueOf(9999L), 9L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class createRandomTicket {

        @DisplayName("count 수 만큼 lotto를 생성해 반환")
        @ParameterizedTest
        @ValueSource(longs = {0L, 1L, 3L, 9L, 99L})
        void should_lottoCountIs_when_givenCount(long count) {
            Money price = Money.valueOf(1000L);
            LottoMachine lottoMachine = LottoMachine.create(price);

            LottoTicket ticket = lottoMachine.createRandomTicket(count);

            Assertions.assertThat(ticket.count()).isEqualTo(count);
        }

        @DisplayName("count가 0보다 작을 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(longs = {-1L, -3L, -5L})
        void should_throwException_when_countUnderZero(long count) {
            Money price = Money.valueOf(1000L);
            LottoMachine lottoMachine = LottoMachine.create(price);

            Assertions.assertThatThrownBy(()->lottoMachine.createRandomTicket(count))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("생성할 로또의 수량이 0보다 작습니다.");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class createReceipt {

        @DisplayName("돈과 구입금액으로 영수증 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnReceiptFromTotalPrice_when_givenMoneyAndCount(Money money, long count, Money totalPrice) {
            Money price = Money.valueOf(1000L);
            LottoMachine lottoMachine = LottoMachine.create(price);
            LottoReceipt expected = LottoReceipt.from(money, totalPrice);

            LottoReceipt actual = lottoMachine.createReceipt(money, count);

            Assertions.assertThat(actual).isEqualTo(expected);
        }

        public List<Arguments> should_returnReceiptFromTotalPrice_when_givenMoneyAndCount() {
            return List.of(
                    Arguments.of(null, 0L, Money.ZERO),
                    Arguments.of(Money.ZERO, 0L, Money.ZERO),
                    Arguments.of(Money.valueOf(1000L), 1L, Money.valueOf(1000L)),
                    Arguments.of(Money.valueOf(1500L), 1L, Money.valueOf(1000L)),
                    Arguments.of(Money.valueOf(3000L), 3L, Money.valueOf(3000L)),
                    Arguments.of(Money.valueOf(9999L), 9L, Money.valueOf(9000L))
            );
        }

        @DisplayName("count가 0보다 작을 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(longs = {-1L, -3L, -5L})
        void should_throwException_when_countUnderZero(long count) {
            Money price = Money.valueOf(1000L);
            LottoMachine lottoMachine = LottoMachine.create(price);

            Assertions.assertThatThrownBy(()->lottoMachine.createReceipt(Money.ZERO, count))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("생성할 로또의 수량이 0보다 작습니다.");
        }
    }
}