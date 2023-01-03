package domain;

import common.state.Result;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class TotalResultTest {

    @Test
    void put() {
        TotalResult totalResult = new TotalResult();
        int previousThree =totalResult.getValueOfResult(Result.THREE);
        totalResult.increaseValueOfResult(Result.THREE);
        totalResult.increaseValueOfResult(Result.THREE);
        int currentThree =totalResult.getValueOfResult(Result.THREE);
        assertThat(currentThree).isEqualTo(previousThree + 2);
    }
}