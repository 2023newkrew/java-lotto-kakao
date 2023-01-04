package util.validator;

import exception.DuplicateNumberException;
import exception.IllegalLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoValidatorTest {
    @DisplayName("입력된 당첨 숫자의 개수가 알맞지 않은지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void validateThrowIllegalLengthExceptionTest(String inputLengthNotSix) {
        assertThrows(IllegalLengthException.class, () -> LottoValidator.validate(inputLengthNotSix));
    }

    @DisplayName("입력된 숫자가 중복될 때 예외 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"2, 2, 3, 4, 5, 6", "1, 1, 1, 1, 1, 1"})
    void validateThrowDuplicateNumberExceptionTest(String inputDuplicate) {
        assertThrows(DuplicateNumberException.class, () -> LottoValidator.validate(inputDuplicate));
    }

    @DisplayName("입력된 숫자가 정상적일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"})
    void validateDoesNotThrowAnyExceptionTest(String properInput) {
        assertDoesNotThrow(() -> LottoValidator.validate(properInput));
    }

}
