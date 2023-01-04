package util.validator;

import domain.Lotto;
import exception.DuplicateNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberValidatorTest {

    @DisplayName("입력한 값에 공백 혹은 숫자가 아닌 글자가 포함되었을 때 예외 확인 테스트")
    @NullAndEmptySource
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "14$"})
    public void validateThrowNumberFormatExceptionTest(String inputWithCharacter) {
        assertThrows(NumberFormatException.class, () -> NumberValidator.validateNumberFormat(inputWithCharacter));
    }

}
