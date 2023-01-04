package lotto.model;

import lotto.exception.InvalidLottoNumberValue;
import lotto.model.LottoCount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoCountTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -3, 0})
    void 음수_혹은_0이면_안됨(int val) {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(val);
        }).isInstanceOf(InvalidLottoNumberValue.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100})
    void 양수이면_정상실행(int val) {
        assertThatCode(() -> {
            LottoCount lottoCount = new LottoCount(val);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(longs = {100, 200, 999})
    void 로또가격보다_작은_Cash일때_안됨(long val) {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(new Cash(val));
        }).isInstanceOf(InvalidLottoNumberValue.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {1000, 10000})
    void 로또가격보다_큰_Cash일떄_정상실행(long val) {
        assertThatCode(() -> {
            LottoCount lottoCount = new LottoCount(new Cash(val));
        }).doesNotThrowAnyException();
    }
}