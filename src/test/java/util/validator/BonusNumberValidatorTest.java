package util.validator;

import domain.WinningLotto;
import exception.DuplicateNumberException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusNumberValidatorTest {

    @DisplayName("보너스 숫자가 당첨 번호와 겹치면 예외 발생")
    @Test
    public void duplicateTest() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");
        int duplicateNumber = 1;
        assertThrows(DuplicateNumberException.class, () -> BonusNumberValidator.validate(winningLotto, duplicateNumber));
    }

    @DisplayName("보너스 숫자가 당첨 번호와 겹치지 않는 경우 통과")
    @Test
    public void notDuplicateTest() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");
        int notDuplicateNumber = 7;
        assertDoesNotThrow( () -> BonusNumberValidator.validate(winningLotto, notDuplicateNumber));
    }

}
