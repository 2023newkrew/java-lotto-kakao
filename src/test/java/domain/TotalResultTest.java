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
        int previousThree = totalResult.getValueOfResult(Result.THREE);
        totalResult.increaseValueOfResult(Result.THREE);
        totalResult.increaseValueOfResult(Result.THREE);
        int currentThree = totalResult.getValueOfResult(Result.THREE);
        assertThat(currentThree).isEqualTo(previousThree + 2);
    }

    @DisplayName("수익률 계산 확인 테스트")
    @Test
    void getProfitTest() {
        int paidPrice = 14_000;
        Map<Result, Integer> seedTotalResult = Map.of(Result.THREE, 1);
        TotalResult totalResult = new TotalResult(seedTotalResult);
        assertThat(totalResult.getProfit(paidPrice)).isEqualTo(0.35);

        Map<Result, Integer> seedTotalResult2 = Map.of(Result.THREE, 2, Result.SIX,1);
        TotalResult totalResult2 = new TotalResult(seedTotalResult2);
        assertThat(totalResult2.getProfit(paidPrice)).isEqualTo(142857.85);
    }
}