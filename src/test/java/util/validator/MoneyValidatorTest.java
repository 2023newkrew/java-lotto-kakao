package util.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyValidatorTest {

    @DisplayName("입력한 값이 음수일 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "-2000", "-3000"})
    void validateThrowNegativeArgumentExceptionTest(String negativeInput) {
        assertThrows(IllegalArgumentException.class, () -> MoneyValidator.validate(negativeInput));
    }

    @DisplayName("금액이 로또 한 장당 가격(1000원)으로 나누어떨어지지 않을 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"100", "1234", "1500"})
    public void validateThrowIllegalArgumentExceptionTest(String inputWithChange) {
        assertThrows(IllegalArgumentException.class, () -> MoneyValidator.validate(inputWithChange));
    }

    @DisplayName("입력한 값이 정상일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "3000"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> MoneyValidator.validate(normalInput));
    }

}
