package lotto.domain;

import static lotto.domain.LottoAnswer.makeLottoAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoAnswerTest {

    @DisplayName("중복된_수를_일반볼로_생성하면_에러가_발생한다")
    @Test
    void 중복된_수를_일반볼로_생성하면_에러가_발생한다() {
        assertThatThrownBy(() -> makeLottoAnswer(List.of(1, 1, 1, 1, 1, 1), 7)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("보너스볼이_일반볼과_중복되면_에러가_발생한다")
    @Test
    void 보너스볼이_일반볼과_중복되면_에러가_발생한다() {
        assertThatThrownBy(() -> makeLottoAnswer(List.of(1, 2, 3, 4, 5, 6), 6)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("보너스볼은_일반볼_범위안에_있어야한다")
    @ParameterizedTest
    @ValueSource(ints = {7,44,45})
    void 보너스볼은_일반볼_범위안에_있어야한다(int input) {
        assertThatCode(() -> makeLottoAnswer(List.of(1, 2, 3, 4, 5, 6), input)).doesNotThrowAnyException();

    }
    @DisplayName("보너스볼은_일반볼_범위안에_없으면_에러가_발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1,0,50})
    void 보너스볼은_일반볼_범위안에_없으면_에러가_발생한다(int input) {
        assertThatThrownBy(() -> makeLottoAnswer(List.of(1, 2, 3, 4, 5, 6), input)).isInstanceOf(IllegalArgumentException.class);
    }

}