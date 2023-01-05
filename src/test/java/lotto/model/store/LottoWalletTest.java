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

import java.math.BigDecimal;
import java.util.List;

class LottoWalletTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("투자금이 없거나 0일 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        @MethodSource
        void should_throwException_when_baseIsNullOrZero(Money base) {
            Assertions.assertThatThrownBy(() -> LottoWallet.create(base))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("투자금은 0원일 수 없습니다.");
        }

        public List<Arguments> should_throwException_when_baseIsNullOrZero() {
            return List.of(Arguments.of(Money.ZERO));
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class buyLottoTicketAutomatically {

        @DisplayName("로또 가격이 1000원일 때 구입한 로또 수량 확인")
        @ParameterizedTest
        @MethodSource
        void should_ticketCountIs_when_givenAmount(long amount, long expected) {
            LottoWallet wallet = LottoWallet.create(Money.valueOf(amount));
            LottoStore store = LottoStore.create(Money.valueOf(1000L));

            wallet.buyLottoTicketAutomatically(store);
            LottoTicket ticket = wallet.getTicket();

            Assertions.assertThat(ticket.size()).isEqualTo(expected);
        }

        public List<Arguments> should_ticketCountIs_when_givenAmount() {
            return List.of(
                    Arguments.of(500L, 0L),
                    Arguments.of(1000L, 1L),
                    Arguments.of(3333L, 3L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getProfitRate {

        @DisplayName("로또를 모두 구입하고 수익률 계산")
        @ParameterizedTest
        @MethodSource
        void should_returnProfitRate_when_givenBalanceAndTotalPrize(long amount, long totalPrizeAmount, BigDecimal expected) {
            LottoWallet wallet = LottoWallet.create(Money.valueOf(amount));
            LottoStore store = LottoStore.create(Money.valueOf(1000L));
            Money totalPrize = Money.valueOf(totalPrizeAmount);

            wallet.buyLottoTicketAutomatically(store);
            BigDecimal profitRate = wallet.getProfitRate(totalPrize);

            Assertions.assertThat(profitRate.doubleValue()).isEqualTo(expected.doubleValue());
        }

        public List<Arguments> should_returnProfitRate_when_givenBalanceAndTotalPrize() {
            return List.of(
                    Arguments.of(1L, 0L, BigDecimal.valueOf(1f)),
                    Arguments.of(1L, 99L, BigDecimal.valueOf(100f)),
                    Arguments.of(1000L, 0L, BigDecimal.valueOf(0f)),
                    Arguments.of(1500L, 1000L, BigDecimal.valueOf(1f))
            );
        }
    }
}