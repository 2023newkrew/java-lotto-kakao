package util.validator;

import domain.Lotto;
import exception.DuplicateNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberValidatorTest {

    @DisplayName("입력한 값이 범위를 벗어났을 때 예외 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-5", "50"})
    void validateThrowIllegalArgumentExceptionTest(String inputInRangeNumber) {
        assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validate(inputInRangeNumber));
    }

    @DisplayName("입력한 값이 정상적인 값일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "4", "45"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> LottoNumberValidator.validate(normalInput));
    }

    @DisplayName("보너스 숫자가 당첨 번호와 겹치면 예외 발생")
    @Test
    public void duplicateTest() {
        Lotto winningLotto = Lotto.getManualLotto("1, 2, 3, 4, 5, 6");
        String duplicateNumber = "1";
        assertThrows(DuplicateNumberException.class, () -> LottoNumberValidator.validate(winningLotto, duplicateNumber));
    }

    @DisplayName("보너스 숫자가 당첨 번호와 겹치지 않는 경우 통과")
    @Test
    public void notDuplicateTest() {
        Lotto winningLotto = Lotto.getManualLotto("1, 2, 3, 4, 5, 6");
        String notDuplicateNumber = "7";
        assertDoesNotThrow( () -> LottoNumberValidator.validate(winningLotto, notDuplicateNumber));
    }

}
