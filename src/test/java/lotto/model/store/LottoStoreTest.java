package lotto.model.store;

import lotto.model.ticket.LottoNumber;
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
import java.util.stream.Collectors;

class LottoStoreTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("로또 머신이 없을 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        void should_throwException_when_priceIsNullOrZero(LottoMachine lottoMachine) {
            Assertions.assertThatThrownBy(() -> LottoStore.create(lottoMachine))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 머신이 없습니다.");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class buyAutomatically {

        @DisplayName("1000원당 로또 1장 반환")
        @ParameterizedTest
        @MethodSource
        void should_lottosCountIs_when_givenMoney(Money money, int lottoCount) {
            LottoStore store = createDefaultStore();

            PurchaseResult purchaseResult = store.buyAutomatically(money);
            LottoTicket ticket = purchaseResult.getTicket();

            Assertions.assertThat(ticket.count()).isEqualTo(lottoCount);
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
            LottoStore store = createDefaultStore();

            PurchaseResult purchaseResult = store.buyAutomatically(money);
            LottoReceipt receipt = purchaseResult.getReceipt();

            Assertions.assertThat(receipt.getChange()).isEqualTo(balance);
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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class buyManually {

        @DisplayName("Money만큼 자동으로 생성한 로또로 수동 생성")
        @ParameterizedTest
        @MethodSource
        void should_equalsAutoAndManual_when_givenMoney(Money money) {
            LottoStore store = createDefaultStore();
            PurchaseResult autoPurchaseResult = store.buyAutomatically(money);
            List<LottoNumber> autoLottos = getLottos(autoPurchaseResult);

            PurchaseResult manualPurchaseResult = store.buyManually(money, autoPurchaseResult.getTicket());
            List<LottoNumber> manualLottos = getLottos(manualPurchaseResult);


            Assertions.assertThatCollection(autoLottos).isEqualTo(manualLottos);
        }

        public List<Arguments> should_equalsAutoAndManual_when_givenMoney() {
            return List.of(
                    Arguments.of((Object) null),
                    Arguments.of(Money.ZERO),
                    Arguments.of(Money.valueOf(1L)),
                    Arguments.of(Money.valueOf(1000L)),
                    Arguments.of(Money.valueOf(1500L)),
                    Arguments.of(Money.valueOf(3000L)),
                    Arguments.of(Money.valueOf(9999L))
            );
        }
    }

    private static List<LottoNumber> getLottos(PurchaseResult autoPurchaseResult) {
        return autoPurchaseResult.getTicket()
                .stream()
                .collect(Collectors.toList());
    }

    private static LottoStore createDefaultStore() {
        Money price = Money.valueOf(1000L);
        LottoMachine lottoMachine = LottoMachine.create(price);

        return LottoStore.create(lottoMachine);
    }
}