package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Test
    @DisplayName("LottoNumber는 캐싱된 데이터를 이용하여 불변 객체이다.")
    void LottoNumber는_캐싱된_데이터를_이용하여_불변_객체이다() {
        assertThat(LottoNumber.from(10)).isSameAs(LottoNumber.from(10));
        assertThat(LottoNumber.from(10)).isNotSameAs(LottoNumber.from(11));
    }

    @Test
    @DisplayName("LottoNumber는 1~45의 값을 가진다.")
    void LottoNumber는_1에서_45의_값을_가진다() {
        assertThatCode(() -> IntStream.rangeClosed(LottoConstants.MIN_LOTTO_NUMBER, LottoConstants.MAX_LOTTO_NUMBER)
                .forEach(LottoNumber::from)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0,46})
    @DisplayName("LottoNumber는 범위외 값이 주어지면 IllegarArgumentException이 발생한다")
    void lottonumber는_범위외_값이_주어지면_illegarargumentexception이_발생한다(int input) {
        assertThatThrownBy(() -> LottoNumber.from(input)).isInstanceOf(IllegalArgumentException.class);
    }

}