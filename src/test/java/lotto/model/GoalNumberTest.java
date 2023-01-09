package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import lotto.model.enums.LottoResultType;
import lotto.model.number.GoalNumber;
import lotto.model.number.LottoNumber;
import org.junit.jupiter.api.Test;

class GoalNumberTest {
    @Test
    public void testCompareLotto() {
        List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer bonusBall = 7;
        GoalNumber goalNumber = new GoalNumber(testNumbers, bonusBall);
        LottoNumber lottoNumber = LottoNumber.create(testNumbers);
        LottoResultType lottoResultType = goalNumber.getLottoResult(lottoNumber);

        assertThat(lottoResultType).isEqualTo(LottoResultType.FIRST);
    }
}