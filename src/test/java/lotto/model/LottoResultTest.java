package lotto.model;

import lotto.exception.InvalidLottoResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoResultTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -3})
    void matchCount가_음수이면_안됨(int val) {
        assertThatThrownBy(() -> {
            LottoResult lottoResult = new LottoResult(val, true);
        }).isInstanceOf(InvalidLottoResult.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 10, 100})
    void matchCount가_7이상이면_안됨(int val) {
        assertThatThrownBy(() -> {
            LottoResult lottoResult = new LottoResult(val, true);
        }).isInstanceOf(InvalidLottoResult.class);
    }

    @Test
    void matchCount가_6이고_matchBonus가_true면_안됨() {
        assertThatThrownBy(() -> {
            LottoResult lottoResult = new LottoResult(6, true);
        }).isInstanceOf(InvalidLottoResult.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void 정상적인_로또결과면_정상실행(int val) {
        assertThatCode(() -> {
            LottoResult lottoResult = new LottoResult(val, false);
        }).doesNotThrowAnyException();
    }
}
