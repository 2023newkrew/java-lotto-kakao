package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {24, 33, 41, 5})
    void 로또_숫자는_1_에서_45_사이의_값이어야_한다(int number) {
        // when, then
        assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_숫자가_1_보다_작거나_45_크면_예외가_발생한다(int number) {
        // when, then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(RuntimeException.class);
    }

}
