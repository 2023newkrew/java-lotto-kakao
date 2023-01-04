package state;

import common.state.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    void createResultTest() {
        Assertions.assertThat(Result.createResult(3, false))
                .isEqualTo(Result.THREE);
        Assertions.assertThat(Result.createResult(4, false))
                .isEqualTo(Result.FOUR);
        Assertions.assertThat(Result.createResult(5, false))
                .isEqualTo(Result.FIVE);
        Assertions.assertThat(Result.createResult(5, true))
                .isEqualTo(Result.FIVEBONUS);
        Assertions.assertThat(Result.createResult(6, false))
                .isEqualTo(Result.SIX);
    }
}
