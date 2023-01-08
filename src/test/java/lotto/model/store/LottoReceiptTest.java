package lotto.model.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;

class LottoReceiptTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("투자금이 10000일 때 총 가격을 받아 영수증 생성")
        @ParameterizedTest
        @MethodSource
        void should_balanceIs_when_givenTotalPrice(Money totalPrice, Money balance) {
            Money base = Money.valueOf(10000L);

            LottoReceipt receipt = LottoReceipt.from(base, totalPrice);

            Assertions.assertThat(receipt.getBalance()).isEqualTo(balance);
        }

        public List<Arguments> should_balanceIs_when_givenTotalPrice() {
            return List.of(
                    Arguments.of(null, Money.valueOf(10000L)),
                    Arguments.of(Money.ZERO, Money.valueOf(10000L)),
                    Arguments.of(Money.valueOf(1000L), Money.valueOf(9000L)),
                    Arguments.of(Money.valueOf(3000L), Money.valueOf(7000L)),
                    Arguments.of(Money.valueOf(9000L), Money.valueOf(1000L)),
                    Arguments.of(Money.valueOf(10000L), Money.ZERO)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class calculateProfitRate {

        @DisplayName("투자금이 1000, 총 가격이 1000일 때 총 상금을 받아 수익률 계산")
        @ParameterizedTest
        @MethodSource
        void should_profitRateIs_when_givenTotalPrize(Money totalPrize, BigDecimal profitRate) {
            Money money = Money.valueOf(1000L);
            LottoReceipt receipt = LottoReceipt.from(money, money);

            BigDecimal actual = receipt.calculateProfitRate(totalPrize);

            Assertions.assertThat(actual).isEqualByComparingTo(profitRate);
        }

        public List<Arguments> should_profitRateIs_when_givenTotalPrize() {
            return List.of(
                    Arguments.of(null, BigDecimal.valueOf(0f)),
                    Arguments.of(Money.ZERO, BigDecimal.valueOf(0f)),
                    Arguments.of(Money.valueOf(1000L), BigDecimal.valueOf(1f)),
                    Arguments.of(Money.valueOf(5000L), BigDecimal.valueOf(5f)),
                    Arguments.of(Money.valueOf(10000L), BigDecimal.valueOf(10f)),
                    Arguments.of(Money.valueOf(100000L), BigDecimal.valueOf(100f))
            );
        }
    }
}