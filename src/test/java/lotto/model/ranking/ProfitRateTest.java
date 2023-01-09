package lotto.model.ranking;

import lotto.model.store.LottoReceipt;
import lotto.model.store.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class ProfitRateTest {


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("영수증이 없을 경우 예외 발생")
        @Test
        void should_throwException_when_givenNullReceipt() {
            Assertions.assertThatThrownBy(() -> ProfitRate.from(null, Money.ZERO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("영수증이 없습니다.");
        }

        @DisplayName("투자금이 1000, 총 가격이 1000일 때 총 상금을 받아 수익률 계산")
        @ParameterizedTest
        @MethodSource
        void should_profitRateIs_when_givenTotalPrize(Money totalPrize, double profitRate) {
            Money money = Money.valueOf(1000L);
            LottoReceipt receipt = LottoReceipt.from(money, money);

            ProfitRate actual = ProfitRate.from(receipt, totalPrize);

            Assertions.assertThat(actual.doubleValue()).isEqualByComparingTo(profitRate);
        }

        public List<Arguments> should_profitRateIs_when_givenTotalPrize() {
            return List.of(
                    Arguments.of(null, 0f),
                    Arguments.of(Money.ZERO, 0f),
                    Arguments.of(Money.valueOf(1000L), 1f),
                    Arguments.of(Money.valueOf(5000L), 5f),
                    Arguments.of(Money.valueOf(10000L), 10f),
                    Arguments.of(Money.valueOf(100000L), 100f)
            );
        }
    }
}