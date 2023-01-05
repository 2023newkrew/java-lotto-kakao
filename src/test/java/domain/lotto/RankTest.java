package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("matchCount가 6일 때는 bonusMatch 여부와 관계없이 FIRST를 반환한다")
    @Test
    void testFirstRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(6, true))).isEqualTo(Rank.FIRST);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(6, false))).isEqualTo(Rank.FIRST);
    }

    @DisplayName("matchCount가 5일 때는 bonusMatch가 true일 때 SECOND, false일 떄 THIRD를 반환한다")
    @Test
    void testSecondThirdTest() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(5, true))).isEqualTo(Rank.SECOND);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(5, false))).isEqualTo(Rank.THIRD);
    }

    @DisplayName("matchCount가 4일 때는 bonusMatch 여부와 관계없이 FIRST를 반환한다")
    @Test
    void testFourthRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(4, true))).isEqualTo(Rank.FOURTH);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(4, false))).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("matchCount가 3일 때는 bonusMatch 여부와 관계없이 FIRST를 반환한다")
    @Test
    void testFifthRank() {
        Assertions.assertThat(Rank.getRank(new LotteryMatch(3, true))).isEqualTo(Rank.FIFTH);
        Assertions.assertThat(Rank.getRank(new LotteryMatch(3, false))).isEqualTo(Rank.FIFTH);
    }

}
