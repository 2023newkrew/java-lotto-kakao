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
    class buyAutomatically {

        @DisplayName("1000원당 로또 1장 반환")
        @ParameterizedTest
        @MethodSource
        void should_lottosCountIs_when_givenMoney(Money money, int lottoCount) {
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            PurchaseResult purchaseResult = store.buyAutomatically(money);
            LottoTicket ticket = purchaseResult.getTicket();

            Assertions.assertThat(ticket.size()).isEqualTo(lottoCount);
        }

        public List<Arguments> should_lottosCountIs_when_givenMoney() {
            return List.of(
                    Arguments.of(null, 0),
                    Arguments.of(Money.ZERO, 0),
                    Arguments.of(Money.valueOf(1L), 0),
                    Arguments.of(Money.valueOf(1000L), 1),
                    Arguments.of(Money.valueOf(1500L), 1),
                    Arguments.of(Money.valueOf(3000L), 3),
                    Arguments.of(Money.valueOf(9999L), 9)
            );
        }

        @DisplayName("1000원당 로또 1장을 구입하고 남은 돈 반환")
        @ParameterizedTest
        @MethodSource
        void should_receiptIs_when_givenMoney(Money money, Money balance) {
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            PurchaseResult purchaseResult = store.buyAutomatically(money);
            LottoReceipt receipt = purchaseResult.getReceipt();

            Assertions.assertThat(receipt.getBalance()).isEqualTo(balance);
        }

        public List<Arguments> should_receiptIs_when_givenMoney() {
            return List.of(
                    Arguments.of(null, Money.ZERO),
                    Arguments.of(Money.ZERO, Money.ZERO),
                    Arguments.of(Money.valueOf(1000L), Money.ZERO),
                    Arguments.of(Money.valueOf(1500L), Money.valueOf(500L)),
                    Arguments.of(Money.valueOf(3000L), Money.ZERO),
                    Arguments.of(Money.valueOf(9999L), Money.valueOf(999L))
            );
        }
    }
}