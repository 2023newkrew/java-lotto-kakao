package lotto.constant;

import static lotto.constant.MessageConstant.INVALID_MATCH_COUNT_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGradeEnumTest {

    @Test
    void 로또의_일치하는_갯수와_보너스_일치_여부를_보내면_등수가_반환된다() {
        assertThat(LottoGradeEnum.getGrade(6, false))
                .isSameAs(LottoGradeEnum.FIRST);
    }

    @Test
    void 이등이_반환된다() {
        assertThat(LottoGradeEnum.getGrade(5, true))
                .isSameAs(LottoGradeEnum.SECOND);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 7})
    void 맞은_갯수는_0이상_6이하이어야_한다(int matchCount) {
        assertThatThrownBy(() -> LottoGradeEnum.getGrade(matchCount, true))
                .hasMessage(INVALID_MATCH_COUNT_RANGE);
    }
}
