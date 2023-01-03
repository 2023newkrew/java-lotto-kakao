package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 숫자가_1_45_사이에_있으면_숫자_객체가_정상적으로_생성됨(int number) {
        assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 숫자가_1_45_사이에_있지_않으면_예외가_발생함(int number) {
        assertThatCode(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

