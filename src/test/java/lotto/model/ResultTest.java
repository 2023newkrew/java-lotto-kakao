package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ResultTest {
    @Test
    void 결과_객체_생성() {
        Map<Grade, Integer> sample = new HashMap<>(){{
            put(Grade.SIX, 3);
            put(Grade.FOUR, 1);
            put(Grade.THREE, 2);
            put(Grade.FIVE_BONUS, 1);
        }};

        Result result = new Result(sample);
        assertThat(result).isEqualTo(new Result(sample));
    }

    @Test
    void 결과_초기화() {
        Result result = new Result();
        assertThat(result).isEqualTo(new Result());
    }

    @Test
    void 자료_추가(){
        Result result = new Result();
        assertThat(result.put(Grade.FIVE, 1)).isEqualTo(1);
    }

    @Test
    void 값_추출(){
        Result result = new Result(){{
            put(Grade.THREE, 3);
            put(Grade.FOUR, 1);
        }};
        assertThat(result.get(Grade.THREE)).isEqualTo(3);
        assertThat(result.get(Grade.FOUR)).isEqualTo(1);
        assertThat(result.get(Grade.SIX)).isEqualTo(0);
    }
}
