package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.constant.MessageConstant.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    void 로또_숫자를_받아_생성한다(int number) {
        assertThat(LottoNumber.from(number)).isEqualTo(LottoNumber.from(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, 100})
    void 로또_숫자는_1부터_45를_벗어나면_예외가_발생한다(int number) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.from(number)
        );
        assertEquals(INVALID_NUMBER_RANGE, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    void 로또_숫자를_반환할_수_있다(int number) {
        assertThat(LottoNumber.from(number).getNumber()).isEqualTo(LottoNumber.from(number));
    }
}
