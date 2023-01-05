package lottov2.model.wallet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

class WalletTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("money를 가지고 있는 지갑 생성")
        @ParameterizedTest
        @ValueSource(longs = {0L, 1L, 100L, 1000L})
        void should_returnWalletHasMoney_when_givenAmount(long amount) {
            Money money = Money.valueOf(amount);

            Wallet wallet = Wallet.create(money);

            Assertions.assertThat(wallet.getBalance()).isEqualTo(money);
        }

        @DisplayName("money가 null일 경우 0원을 가지고 있는 지갑 생성")
        @ParameterizedTest
        @NullSource
        void should_returnWalletHasZeroMoney_when_givenNull(Money money) {
            Wallet wallet = Wallet.create(money);

            Assertions.assertThat(wallet.getBalance()).isEqualTo(Money.ZERO);
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getProfitRate {
        @DisplayName("아무 행동도 하지 않았을 경우 1 반환")
        @ParameterizedTest
        @ValueSource(longs = {1L, 100L, 1000L})
        void should_returnOne_when_doNothing(long amount) {
            Money money = Money.valueOf(amount);

            Wallet wallet = Wallet.create(money);

            Assertions.assertThat(wallet.getProfitRate()).isEqualTo(BigDecimal.valueOf(1L));
        }

        @DisplayName("Money가 0일 경우 예외 발생")
        @Test
        void should_throwException_when_givenZeroMoney() {
            Wallet wallet = Wallet.create(Money.valueOf(0L));

            Assertions.assertThatThrownBy(() -> wallet.getProfitRate())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("투자금이 0원일 경우 수익률을 계산할 수 없습니다.");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class withdraw {

        @DisplayName("잔액보다 큰 금액을 인출할 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(longs = {1001L, 2000L, 3000L})
        void should_throwException_when_balanceLessAmount(long amount) {
            Wallet wallet = Wallet.create(Money.valueOf(1000L));
            Money money = Money.valueOf(amount);

            Assertions.assertThatThrownBy(() -> wallet.withdraw(money))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잔액보다 큰 금액은 인출할 수 없습니다.");
        }

        @DisplayName("인출 성공")
        @ParameterizedTest
        @CsvSource({"0,1000","1,999","100,900","500,500","1000,0"})
        void should_returnBalanceSubtractedAmount_when_givenAmount(long amount, long expected) {
            Wallet wallet = Wallet.create(Money.valueOf(1000L));
            Money money = Money.valueOf(amount);
            Money balance = Money.valueOf(expected);

            wallet.withdraw(money);

            Assertions.assertThat(wallet.getBalance()).isEqualTo(balance);
        }
    }
}