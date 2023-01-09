package lotto;

import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("1~45 사이의 숫자 생성")
    @ParameterizedTest
    @ValueSource(ints={1, 2, 44, 45})
    void create_number_between_1_and_45(int value) {
        Assertions.assertThatCode(() -> new LottoNumber(value)).doesNotThrowAnyException();
    }

    @DisplayName("1~45 사이의 이외의 숫자 생성시 실패")
    @ParameterizedTest
    @ValueSource(ints={0, 46})
    void fail_to_create_number_other_than_between_1_and_45(int value) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
