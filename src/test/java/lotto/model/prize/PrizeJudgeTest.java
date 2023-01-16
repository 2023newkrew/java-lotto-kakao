package lotto.model.prize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

public class PrizeJudgeTest {
    private static final String FIRST = "6,false,2000000000";
    private static final String SECOND = "5,true,30000000";
    private static final String THIRD = "5,false,1500000";
    private static final String FOURTH1 = "4,true,50000";
    private static final String FOURTH2 = "4,false,50000";
    private static final String FIFTH1 = "3,true,5000";
    private static final String FIFTH2 = "3,false,5000";
    private static final String LOSING = "2,true,0";

    @ParameterizedTest
    @CsvSource(value = {FIRST, SECOND, THIRD, FOURTH1, FOURTH2, FIFTH1, FIFTH2, LOSING})
    @DisplayName("can judge prize according to number match")
    void can_judge_prize_according_to_number_match(int matchCount, boolean hasBonus, long prize) {
        assertEquals(prize, PrizeJudge.getPrizeOf(matchCount, hasBonus).prize());
    }
}
