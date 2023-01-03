package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoResultTest {
    @DisplayName("로또 결과를 지정된 값으로 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void 로또_결과를_지정된_값으로_생성한다(int count) {
        Assertions.assertThatCode(() -> new LottoResult(count, false))
                .doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 결과에 대해 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 13})
    void 유효하지_않은_결과에_대해_예외를_발생시킨다(int count) {
        Assertions.assertThatThrownBy(() -> new LottoResult(count, false))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
