package lotto.domain;

import lotto.domain.number.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("1~45 사이의 숫자 생성")
    @ParameterizedTest
    @ValueSource(ints={1, 2, 44, 45})
    void createNumberBetween1And45(int value) {
        Assertions.assertThatCode(() -> LottoNumber.from(value)).doesNotThrowAnyException();
    }

    @DisplayName("1~45 사이의 이외의 숫자 생성시 실패")
    @ParameterizedTest
    @ValueSource(ints={0, 46})
    void failToCreateNumberOtherThanBetween1And45(int value) {
        Assertions.assertThatThrownBy(() -> LottoNumber.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
