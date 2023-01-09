package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGradeEnumTest {

    @Test
    void 로또의_일치하는_갯수와_보너스_일치_여부를_보내면_등수가_반환된다() {
        assertThat(LottoGradeEnum.evaluate(6, false))
                .isSameAs(LottoGradeEnum.FIRST);
    }

    @Test
    void 이등이_반환된다() {
        assertThat(LottoGradeEnum.evaluate(5, true))
                .isSameAs(LottoGradeEnum.SECOND);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 7})
    void 맞은_갯수는_0이상_6이하이어야_한다(int matchCount) {
        assertThatThrownBy(() -> LottoGradeEnum.evaluate(matchCount, true));
    }


    @ParameterizedTest
    @MethodSource("enumEvaluateArgument")
    void 평가_테스트(int matchCount, boolean isMatchBonus, LottoGradeEnum expected) {
        assertThat(LottoGradeEnum.evaluate(matchCount, isMatchBonus)).isSameAs(expected);
    }

    static Stream<Arguments> enumEvaluateArgument() {
        return Stream.of(
                Arguments.arguments(LottoGradeEnum.FIRST.getMatchCount(), false, LottoGradeEnum.FIRST),
                Arguments.arguments(LottoGradeEnum.SECOND.getMatchCount(), true, LottoGradeEnum.SECOND),
                Arguments.arguments(LottoGradeEnum.THIRD.getMatchCount(), false, LottoGradeEnum.THIRD),
                Arguments.arguments(LottoGradeEnum.FOURTH.getMatchCount(), false, LottoGradeEnum.FOURTH),
                Arguments.arguments(LottoGradeEnum.FIFTH.getMatchCount(), false, LottoGradeEnum.FIFTH),
                Arguments.arguments(LottoGradeEnum.NONE_GRADE.getMatchCount(), false, LottoGradeEnum.NONE_GRADE)
        );
    }

}
