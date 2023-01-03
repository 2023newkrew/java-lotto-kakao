package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import lotto.common.LottoResult;
import lotto.model.number.Goal;
import lotto.model.number.Lotto;
import org.junit.jupiter.api.Test;

class GoalTest {
    @Test
    public void testCompareLotto() {
        List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer bonusBall = 7;
        Goal goal = new Goal(testNumbers, bonusBall);
        Lotto lotto = new Lotto(testNumbers);
        LottoResult lottoResult = goal.compareLotto(lotto);

        assertThat(lottoResult).isEqualTo(LottoResult.FIRST);
    }
}