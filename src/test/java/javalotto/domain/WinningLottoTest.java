package javalotto.domain;

import javalotto.exception.winninglotto.BonusNumberDuplicateException;
import javalotto.exception.winninglotto.BonusNumberOutOfRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    void should_createSuccessfully_when_validLottoAndBonusNumber() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        WinningLotto result = WinningLotto.of(lotto, bonusNumber);

        assertThat(result).isNotNull();
    }

    @Test
    void should_throwException_when_duplicateBonusNumber() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 5;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(BonusNumberDuplicateException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void should_throwException_when_outOfRangeBonusNumber(int bonusNumber) {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(BonusNumberOutOfRangeException.class);
    }
}