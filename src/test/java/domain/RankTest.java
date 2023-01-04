package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @MethodSource("provideMatchCount")
    void 일치하는_숫자_개수와_보너스_볼_일치_여부에_따라_등수가_결정된다(int matchCount, boolean isMatchWithBonusBall, Rank expectedRank) {
        // when, then
        assertThat(Rank.findRank(matchCount, isMatchWithBonusBall)).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideMatchCount() {
        return Stream.of(
                Arguments.of(6, false, FIRST_PLACE),
                Arguments.of(5, true, SECOND_PLACE),
                Arguments.of(5, false, THIRD_PLACE),
                Arguments.of(4, false, FOURTH_PLACE),
                Arguments.of(3, false, FIFTH_PLACE),
                Arguments.of(2, false, null)
        );
    }

}
