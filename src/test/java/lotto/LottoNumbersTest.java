package lotto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    @Test
    @DisplayName("범위에_없는_숫자가_주어지면_에러가_발생한다")
    void 범위에_없는_숫자가_주어지면_에러가_발생한다() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(1,2,3,4,5,47)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoNumbers(List.of(-1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_6개가_아니면_에러가_발생한다(){
        assertThatThrownBy(()->new LottoNumbers(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_6개_주어지면_생성되어야한다(){
        assertThatCode(()->new LottoNumbers(List.of(1,2,3,4,5,6)))
                .doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_주어지면_에러가_발생한다(){
        assertThatThrownBy(()->new LottoNumbers(List.of(1,2,3,4,5,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}