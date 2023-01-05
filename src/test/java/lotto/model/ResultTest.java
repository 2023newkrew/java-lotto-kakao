package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ResultTest {
    @Test
    void Result_객체가_정상적으로_생성되어야함() {
        Map<Grade, Integer> sample = new HashMap<>() {{
            put(Grade.SIX, 3);
            put(Grade.FOUR, 1);
            put(Grade.THREE, 2);
            put(Grade.FIVE_BONUS, 1);
        }};

        Result result = new Result(sample);
        assertThat(result).isEqualTo(new Result(sample));
    }

    @Test
    void 값의_추가_또는_증가가_횟수만큼_되어야함() {
        Result result = new Result();
        result.addUp(Grade.FIVE);
        assertThat(result.addUp(Grade.FIVE)).isEqualTo(2);
    }

    @Test
    void 값을_설정된대로_획득할_수_있어야함() {
        Result result = new Result(new HashMap<>() {{
            put(Grade.THREE, 1);
            put(Grade.FOUR, 2);
            put(Grade.FIVE, 3);
        }});
        assertThat(result.getValue(Grade.THREE)).isEqualTo(1);
        assertThat(result.getValue(Grade.FOUR)).isEqualTo(2);
        assertThat(result.getValue(Grade.FIVE)).isEqualTo(3);
        assertThat(result.getValue(Grade.FIVE_BONUS)).isEqualTo(0);
    }

    @Test
    void 수익률이_정확히_계산되어야_함() {
        Result result = new Result(new HashMap<>() {{
            put(Grade.THREE, 1);
            put(Grade.FOUR, 1);
            put(Grade.FIVE, 1);
            put(Grade.FIVE_BONUS, 1);
            put(Grade.SIX, 1);
        }});
        assertThat(result.getProfitRate(5000)).isEqualTo((double) 2031555000 / (double) 5000);
    }
}
