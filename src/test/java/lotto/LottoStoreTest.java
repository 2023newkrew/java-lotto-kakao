package lotto;

import lotto.model.*;
import lotto.model.generator.LottoGenerator;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.vo.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static lotto.TestUtil.toLottoNumbers;

class LottoStoreTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("로또 생성기가 없을 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        void should_throwException_when_generatorIsNull(LottoGenerator lottoGenerator) {
            Assertions.assertThatThrownBy(() -> LottoStore.create(lottoGenerator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 생성기가 필요합니다.");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class buyLotto {

        @DisplayName("입력 금액 만큼 로또 발행")
        @ParameterizedTest
        @CsvSource(value = {"1000, 1", "2000, 2", "3333, 3", "9999, 9"})
        void should_returnBundleHasLottos_when_givenMoney(int moneyAmount, int lottoCount) {
            Money money = Money.valueOf(moneyAmount);
            LottoGenerator lottoGenerator = () -> Lotto.from(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
            LottoStore lottoStore = LottoStore.create(lottoGenerator);

            PurchaseResult purchaseResult = lottoStore.buyLotto(money);
            LottoBundle lottoBundle = purchaseResult.getLottoBundle();

            Assertions.assertThat(lottoBundle.size()).isEqualTo(lottoCount);
        }

        @DisplayName("로또의 총 금액을 반환")
        @ParameterizedTest
        @CsvSource(value = {"1000, 1000", "2000, 2000", "3333, 3000", "9999, 9000"})
        void should_returnBundleHasTotalPrice_when_givenMoney(int moneyAmount, long expected) {
            Money money = Money.valueOf(moneyAmount);
            LottoGenerator lottoGenerator = () -> Lotto.from(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
            LottoStore lottoStore = LottoStore.create(lottoGenerator);

            PurchaseResult purchaseResult = lottoStore.buyLotto(money);
            Money totalPrice = purchaseResult.getTotalPrice();

            Assertions.assertThat(totalPrice.longValue()).isEqualTo(expected);
        }
    }
}