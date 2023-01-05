package lottoTest.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.PurchaseAmount;
import lotto.service.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PurchaseAmountTest {
    @ParameterizedTest
    @MethodSource("validateWrongPurchaseAmountTestGenerator")
    @DisplayName("로또 번호로 유효하지 않은 값이 입력되면 예외 반환")
    public void validateWrongPurchaseAmountTest(String purchaseAmountString, ErrorCode expected) {
        //given

        //when & then
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmountString))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());
    }

    private static Stream<Arguments> validateWrongPurchaseAmountTestGenerator() {
        return Stream.of(
                Arguments.of("abc", ErrorCode.INVALID_INPUT_TYPE_NOT_INTEGER),
                Arguments.of("-1", ErrorCode.INVALID_PURCHASE_AMOUNT)
        );
    }

    @Test
    @DisplayName("로또를 살 돈이 부족한 경우 발생하는 예외 테스트")
    public void lackOfMoneyTest() {
        //given
        Integer lottoPrice = LottoService.getLottoPrice();
        //when & then
        assertThatThrownBy(() -> new PurchaseAmount(String.valueOf(lottoPrice - 1)))
                .isInstanceOf(LottoException.class)
                .hasMessage(ErrorCode.LACK_OF_MONEY.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getLottoTicketCountTestGenerator")
    @DisplayName("로또를 살 개수를 구하는 로직 테스트")
    public void getLottoTicketCountTest(PurchaseAmount purchaseAmount, int expected) {
        //given
        Integer lottoPrice = 1000;
        //when & then
        assertThat(purchaseAmount.getLottoTicketCount(lottoPrice)).isEqualTo(expected);
    }

    private static Stream<Arguments> getLottoTicketCountTestGenerator() {
        return Stream.of(
                Arguments.of(new PurchaseAmount("10000"), 10),
                Arguments.of(new PurchaseAmount("200000"), 200),
                Arguments.of(new PurchaseAmount("15239"), 15)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateRateOfReturnTestGenerator")
    @DisplayName("로또 구매 후 이익률을 구하는 로직 테스트")
    public void calculateRateOfReturnTest(PurchaseAmount purchaseAmount, double expected) {
        //given
        int totalRevenue = 3000;
        //when & then
        assertThat(purchaseAmount.calculateRateOfReturn(totalRevenue)).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateRateOfReturnTestGenerator() {
        return Stream.of(
                Arguments.of(new PurchaseAmount("1000"), 3d),
                Arguments.of(new PurchaseAmount("3000"), 1d),
                Arguments.of(new PurchaseAmount("6000"), 0.5d)
        );
    }

}
