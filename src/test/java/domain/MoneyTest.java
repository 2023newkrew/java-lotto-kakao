package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @DisplayName("숫자가 아닌 문자열 입력 시 예외 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "14$"})
    void validateNumberFormatTest(String inputWithCharacter) {
        assertThrows(NumberFormatException.class, () -> new Money(inputWithCharacter));
    }

    @DisplayName("입력한 값이 음수일 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "-2000", "-3000"})
    void validateThrowNegativeArgumentExceptionTest(String negativeInput) {
        assertThrows(IllegalArgumentException.class, () -> new Money(negativeInput));
    }

    @DisplayName("금액이 로또 한 장당 가격(1000원)으로 나누어떨어지지 않을 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"100", "1234", "1500"})
    public void validateThrowIllegalArgumentExceptionTest(String inputWithChange) {
        assertThrows(IllegalArgumentException.class, () -> new Money(inputWithChange));
    }

    @DisplayName("입력한 값이 정상일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "3000"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> new Money(normalInput));
    }

}
