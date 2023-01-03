package util.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningNumbersValidatorTest {
    @DisplayName("입력된 당첨 숫자의 개수가 알맞지 않은지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void validateLengthThrowIllegalArgumentExceptionTest(String inputLengthNotSix) {
        assertThrows(NumberFormatException.class, () -> WinningNumbersValidator.validate(inputLengthNotSix));
    }

    @DisplayName("입력된 당첨 숫자의 개수가 알맞은지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"})
    void validateLengthDoesNotThrowIllegalArgumentExceptionTest(String inputLengthIsSix) {
        assertDoesNotThrow(() -> WinningNumbersValidator.validate(inputLengthIsSix));
    }

    @DisplayName("입력된 숫자가 모두 다른지 확인하는 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"2, 2, 3, 4, 5, 6", "1, 1, 1, 1, 1, 1"})
    void validateDuplicateThrowIllegalArgumentExceptionTest(String inputDuplicate) {
        assertThrows(IllegalArgumentException.class, () -> WinningNumbersValidator.validate(inputDuplicate));
    }

}
