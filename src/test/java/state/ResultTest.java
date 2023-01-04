package state;

import common.state.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    void createResultTest() {
        Assertions.assertThat(Result.createResult(3, false))
                .isEqualTo(Result.FIFTH);
        Assertions.assertThat(Result.createResult(4, false))
                .isEqualTo(Result.FOURTH);
        Assertions.assertThat(Result.createResult(5, false))
                .isEqualTo(Result.THIRD);
        Assertions.assertThat(Result.createResult(5, true))
                .isEqualTo(Result.SECOND);
        Assertions.assertThat(Result.createResult(6, false))
                .isEqualTo(Result.FIRST);
    }
}
