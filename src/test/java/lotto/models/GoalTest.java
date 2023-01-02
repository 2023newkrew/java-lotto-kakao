package lotto.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class GoalTest {
    @Test
    public void testCompareLotto() {
        List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer bonusBall = 7;
        Goal goal = new Goal(testNumbers, bonusBall);
        Lotto lotto = new Lotto(testNumbers);
        LottoResult lottoResult = goal.compareLotto(lotto, bonusBall);
        LottoResult targetLottoResult = new LottoResult(6, false);

        assertThat(lottoResult).isEqualTo(targetLottoResult);
    }
}