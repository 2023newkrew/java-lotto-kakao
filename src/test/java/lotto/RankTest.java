package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("getRank 6개 매칭 결과 테스트")
    @Test
    void testFirstRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(6, true))).isEqualTo(Rank.FIRST);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(6, false))).isEqualTo(Rank.FIRST);
    }

    @DisplayName("getRank 5개 매칭 결과 테스트")
    @Test
    void testSecondThirdTest() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(5, true))).isEqualTo(Rank.SECOND);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(5, false))).isEqualTo(Rank.THIRD);
    }

    @DisplayName("getRank 6개 매칭 결과 테스트")
    @Test
    void testFourthRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(4, true))).isEqualTo(Rank.FOURTH);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(4, false))).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("getRank 3개 매칭 결과 테스트")
    @Test
    void testFifthRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(3, true))).isEqualTo(Rank.FIFTH);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(3, false))).isEqualTo(Rank.FIFTH);
    }

}
