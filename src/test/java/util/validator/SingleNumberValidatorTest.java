package util.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleNumberValidatorTest {

    @DisplayName("입력한 값에 숫자가 아닌 글자가 포함되었을 때 예외 확인 테스트")
    @NullAndEmptySource
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "14$"})
    public void validateThrowNumberFormatExceptionTest(String inputWithCharacter) {
        assertThrows(NumberFormatException.class, () -> SingleNumberValidator.validate(inputWithCharacter));
    }

    @DisplayName("입력한 값이 범위를 벗어났을 때 예외 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-5", "50"})
    void validateThrowIllegalArgumentExceptionTest(String inputInRangeNumber) {
        assertThrows(IllegalArgumentException.class, () -> SingleNumberValidator.validate(inputInRangeNumber));
    }

    @DisplayName("입력한 값이 정상적인 값일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "4", "45"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> SingleNumberValidator.validate(normalInput));
    }

}
