package lottoTest.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
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
}
