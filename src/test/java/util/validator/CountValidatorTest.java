package util.validator;

import domain.Lotto;
import exception.DuplicateNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountValidatorTest {

    private static int totalCount;

    @BeforeEach
    void setUp() {
        totalCount = 10;
    }

    @DisplayName("입력한 값이 음수일 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-3"})
    void validateThrowNegativeArgumentExceptionTest(String negativeInput) {
        assertThrows(IllegalArgumentException.class, () -> CountValidator.validate(negativeInput, totalCount));
    }

    @DisplayName("뽑고자 하는 수동 로또가 뽑을 수 있는 총 로또 개수보다 클 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"11", "12", "13"})
    public void validateThrowIllegalArgumentExceptionTest(String bigInput) {
        assertThrows(IllegalArgumentException.class, () -> CountValidator.validate(bigInput, totalCount));
    }

    @DisplayName("입력한 값이 정상일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> CountValidator.validate(normalInput, totalCount));
    }

}
