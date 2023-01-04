package domain;

import common.state.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TotalResultTest {

    @DisplayName("당첨 결과 증가 테스트")
    @Test
    void increaseValueOfResultTest() {
        TotalResult totalResult = new TotalResult();
        int previousThree = totalResult.getValueOfResult(Result.FIFTH_PLACE);
        totalResult.increaseValueOfResult(Result.FIFTH_PLACE);
        totalResult.increaseValueOfResult(Result.FIFTH_PLACE);
        int currentThree = totalResult.getValueOfResult(Result.FIFTH_PLACE);
        assertThat(currentThree).isEqualTo(previousThree + 2);
    }

    @DisplayName("수익률 계산 확인 테스트")
    @Test
    void getProfitTest() {
        int paidPrice = 14_000;
        Map<Result, Integer> seedTotalResult = Map.of(Result.FIFTH_PLACE, 1);
        TotalResult totalResult = new TotalResult(seedTotalResult);
        assertThat(totalResult.getProfit(paidPrice)).isEqualTo(0.35);

        Map<Result, Integer> seedTotalResult2 = Map.of(Result.FIFTH_PLACE, 2, Result.FIRST_PLACE,1);
        TotalResult totalResult2 = new TotalResult(seedTotalResult2);
        assertThat(totalResult2.getProfit(paidPrice)).isEqualTo(142857.85);
    }
}