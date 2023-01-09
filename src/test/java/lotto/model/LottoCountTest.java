package lotto.model;

import lotto.exception.InvalidLottoNumberValue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoCountTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -3})
    void 음수이면_안됨(int val) {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(val);
        }).isInstanceOf(InvalidLottoNumberValue.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100})
    void 양수_또는_0이면_정상실행(int val) {
        assertThatCode(() -> {
            LottoCount lottoCount = new LottoCount(val);
        }).doesNotThrowAnyException();
    }
}