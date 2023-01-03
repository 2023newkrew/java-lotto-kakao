package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import lotto.common.LottoResult;
import lotto.model.number.GoalNumber;
import lotto.model.number.LottoNumber;
import org.junit.jupiter.api.Test;

class GoalNumberTest {
    @Test
    public void testCompareLotto() {
        List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer bonusBall = 7;
        GoalNumber goalNumber = new GoalNumber(testNumbers, bonusBall);
        LottoNumber lottoNumber = new LottoNumber(testNumbers);
        LottoResult lottoResult = goalNumber.getLottoResultByCompareLotto(lottoNumber);

        assertThat(lottoResult).isEqualTo(LottoResult.FIRST);
    }
}