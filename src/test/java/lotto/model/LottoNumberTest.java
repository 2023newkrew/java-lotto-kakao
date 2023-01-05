package lotto.model;

import lotto.model.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
}
