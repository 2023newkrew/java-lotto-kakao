package lotto.models;

import static lotto.common.LottoConfiguration.MAX_VALUE;
import static lotto.common.LottoConfiguration.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GoalTest {

    @Nested
    @DisplayName("로또 등수 테스트")
    public class LottoRankTest {
        @Test
        @DisplayName("1등 로또 테스트")
        public void testFirstRankLotto() {
            List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
            Integer bonusBall = 7;
            Goal goal = new Goal(testNumbers, bonusBall);
            Lotto lotto = new Lotto(testNumbers);
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.FIRST);
        }

        @Test
        @DisplayName("2등 로또 테스트")
        public void testSecondRankLotto() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.SECOND);
        }

        @Test
        @DisplayName("3등 로또 테스트")
        public void testThirdRankLotto() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.THIRD);
        }

        @Test
        @DisplayName("4등 로또 테스트")
        public void testFourthRankLotto() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 8, 9));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.FOURTH);
        }

        @Test
        @DisplayName("5등 로또 테스트")
        public void testFifthRankLotto() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 8, 9, 10));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.FIFTH);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 2개 일치")
        public void testNoneRankLotto_2Match() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 14, 8, 9, 10));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.NONE);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 1개 일치")
        public void testNoneRankLotto_1Match() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(1, 13, 14, 8, 9, 10));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.NONE);
        }

        @Test
        @DisplayName("당첨 안된 로또 테스트 - 0개 일치")
        public void testNoneRankLotto_0Match() {
            Integer bonusBall = 7;
            Goal goal = new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), bonusBall);
            Lotto lotto = new Lotto(Arrays.asList(12, 13, 14, 8, 9, 10));
            LottoResult lottoResult = goal.compareLotto(lotto);

            assertThat(lottoResult).isEqualTo(LottoResult.NONE);
        }
    }

    @Nested
    @DisplayName("잘못된 Goal 생성 테스트")
    public class CreateInvalidGoalTest {
        @Test
        @DisplayName("Bonus Ball이 로또 번호와 겹치는 Goal 생성 테스트")
        public void testDuplicatedBonusBall() {
            assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                            () -> new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), 6))
                    .withMessage("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }

        @Test
        @DisplayName("잘못된 범위의 Bonus Ball 테스트")
        public void testInvalidRangeBonusBall() {
            assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                            () -> new Goal(Arrays.asList(1, 2, 3, 4, 5, 6), -3))
                    .withMessage("보너스 볼은 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

}