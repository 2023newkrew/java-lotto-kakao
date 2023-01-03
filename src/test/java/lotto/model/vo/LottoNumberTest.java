package lotto.model.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LottoNumberTest {

    @Nested
    class createNumberPool {

        @DisplayName("1-45 사이의 숫자들이 생성된다.")
        @Test
        void should_returnNumberPool_when_calling() {
            List<LottoNumber> expected = IntStream.rangeClosed(1, 45)
                    .mapToObj(LottoNumber::valueOf)
                    .collect(Collectors.toList());

            List<LottoNumber> lottoNumbers = LottoNumber.createNumberPool();

            Assertions.assertThatCollection(lottoNumbers).isEqualTo(expected);
        }
    }

    @Nested
    class valueOf {

        @DisplayName("잘못된 범위의 로또 번호는 생성할 수 없다.")
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 46, 47})
        void should_throwException_when_valueOutOfRange(int value) {
            Assertions.assertThatThrownBy(() -> LottoNumber.valueOf(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1 ~ 45 사이의 숫자입니다.");
        }
    }
}
