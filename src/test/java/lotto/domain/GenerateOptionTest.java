package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GenerateOptionTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void 옵션_값은_1_혹은_2로_생성된다(int generateOptionInput) {
        assertThatCode(() -> new GenerateOption(generateOptionInput)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 100, 0, -1, -2, -100})
    void 옵션이_1_또는_2가_아닌_값이_들어오면_예외를_반환한다(int generateOptionInput) {
        assertThatThrownBy(() -> new GenerateOption(generateOptionInput)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동옵션이면_입력_필요_메시지를_보냈을_때_false를_반환해야_한다() {
        GenerateOption generateOption = new GenerateOption(1);
        assertThat(generateOption.isInputRequired()).isFalse();
    }

    @Test
    void 수동옵션이면_입력_필요_메시지를_보냈을_때_true를_반환해야_한다() {
        GenerateOption generateOption = new GenerateOption(2);
        assertThat(generateOption.isInputRequired()).isTrue();
    }
}
