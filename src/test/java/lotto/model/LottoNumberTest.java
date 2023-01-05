package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("잘못된 범위의 로또 번호는 생성할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void outOfRangeNumber(int value) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이의 숫자입니다.");
    }

    @DisplayName("로또 번호를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 13, 34, 45})
    void createValidLottoNumber(int number) {
        Assertions.assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 풀은 1~45의 숫자를 한개씩 가지고 있다.")
    @Test
    void createPool() {
        List<LottoNumber> actual = LottoNumber.createPool();

        List<LottoNumber> expected = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());

        Assertions.assertThat(actual)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
