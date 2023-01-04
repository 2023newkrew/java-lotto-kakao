package lotto.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class GradeTest {
    @ParameterizedTest
    @CsvSource(value = {
            "2:ZERO",
            "3:THREE",
            "4:FOUR",
            "5:FIVE",
            "15:FIVE_BONUS",
            "6:SIX"
    }, delimiter = ':')
    void 개수에_따라_적절한_ENUM_을_반환해야_함(int count, Grade result) {
        assertThat(Grade.getGrade(count)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ZERO:0",
            "THREE:5000",
            "FOUR:50000",
            "FIVE:1500000",
            "FIVE_BONUS:30000000",
            "SIX:2000000000"
    }, delimiter = ':')
    void 각각의_GRADE_에_맞는_상금이_반환되어야_함(Grade grade, int result) {
        assertThat(grade.getReward()).isEqualTo(result);
    }
}