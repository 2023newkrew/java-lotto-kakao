package lotto.constant;

import static lotto.constant.MessageConstant.INVALID_MATCH_COUNT_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGradeTest {
    @Test
    void 로또_일치수와_보너스_일치여부를_보내면_등수가_반환된다() {
        assertThat(LottoGrade.getGrade(6, false))
                .isSameAs(LottoGrade.FIRST);
    }

    @Test
    void 이등이_반환된다() {
        assertThat(LottoGrade.getGrade(5, true))
                .isSameAs(LottoGrade.SECOND);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 7})
    void 같은_숫자_필요_개수는_0이상_6이하이어야_한다(int matchCount) {
        assertThatThrownBy(() -> LottoGrade.getGrade(matchCount, true))
                .hasMessage(INVALID_MATCH_COUNT_RANGE);
    }
}
