package lotto.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import lotto.models.enums.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningLotteryTest {

    static public final Integer MIN_VALUE = 1;

    static public final Integer MAX_VALUE = 45;

    @Nested
    @DisplayName("로또 등수 테스트")
    public class LotteryRankTest {
        @Test
        @DisplayName("1등 로또 테스트")
        public void testFirstRankLottery() {
            List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(testNumbers, bonusBall);
            Lottery lottery = new Lottery(testNumbers);
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.FIRST);
        }

        @Test
        @DisplayName("2등 로또 테스트")
        public void testSecondRankLottery() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 2, 3, 4, 5, 7));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.SECOND);
        }

        @Test
        @DisplayName("3등 로또 테스트")
        public void testThirdRankLottery() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 2, 3, 4, 5, 8));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.THIRD);
        }

        @Test
        @DisplayName("4등 로또 테스트")
        public void testFourthRankLottery() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 2, 3, 4, 8, 9));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.FOURTH);
        }

        @Test
        @DisplayName("5등 로또 테스트")
        public void testFifthRankLottery() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 2, 3, 8, 9, 10));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.FIFTH);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 2개 일치")
        public void testNoneRankLottery_2Match() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 2, 14, 8, 9, 10));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.NONE);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 1개 일치")
        public void testNoneRankLottery_1Match() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(1, 13, 14, 8, 9, 10));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.NONE);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 0개 일치")
        public void testNoneRankLottery_0Match() {
            Integer bonusBall = 7;
            WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lottery lottery = new Lottery(Arrays.asList(12, 13, 14, 8, 9, 10));
            Rank rank = winningLottery.compareLottery(lottery);

            assertThat(rank).isEqualTo(Rank.NONE);
        }
    }

    @Nested
    @DisplayName("잘못된 Goal 생성 테스트")
    public class CreateInvalidWinningLotteryTest {
        @Test
        @DisplayName("Bonus Ball이 로또 번호와 겹치는 Goal 생성 테스트")
        public void testDuplicatedBonusBall() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 6))
                    .withMessage("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }

        @Test
        @DisplayName("잘못된 범위의 Bonus Ball 테스트")
        public void testInvalidRangeBonusBall() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), -3))
                    .withMessage("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

}