package domain;

import common.state.Result;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TotalResultTest {

    @Test
    void increaseValueOfResultTest() {
        TotalResult totalResult = new TotalResult();
        int previousThree =totalResult.getValueOfResult(Result.FIFTH);
        totalResult.increaseValueOfResult(Result.FIFTH);
        totalResult.increaseValueOfResult(Result.FIFTH);
        int currentThree =totalResult.getValueOfResult(Result.FIFTH);
        assertThat(currentThree).isEqualTo(previousThree + 2);
    }

    @Test
    void getProfitTest() {
        int paidPrice = 14_000;
        Map<Result, Integer> seedTotalResult = Map.of(Result.FIFTH, 1);
        TotalResult totalResult = new TotalResult(seedTotalResult);
        assertThat(totalResult.getProfit(paidPrice)).isEqualTo(0.35);

        Map<Result, Integer> seedTotalResult2 = Map.of(Result.FIFTH, 2, Result.FIRST,1);
        TotalResult totalResult2 = new TotalResult(seedTotalResult2);
        assertThat(totalResult2.getProfit(paidPrice)).isEqualTo(142857.85);
    }
}
