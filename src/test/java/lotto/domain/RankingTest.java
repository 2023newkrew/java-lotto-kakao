package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankingTest {
    @DisplayName("당첨 번호 일치 개수와 보너스 번호 일치 여부로 당첨 결과를 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "6,false,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,true,FOURTH",
            "4,false,FOURTH"
    })
    void matchRanking(int matchingCount, boolean matchBonusBall, Ranking ranking) {
        assertThat(Ranking.matchRanking(matchingCount, matchBonusBall)).isEqualTo(ranking);
    }
}