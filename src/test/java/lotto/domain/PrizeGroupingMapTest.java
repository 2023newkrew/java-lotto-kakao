package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrizeGroupingMapTest {

    private final NumberList numbers = new NumberList(List.of(1, 2, 3, 4, 5, 6));

    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = numbers.toLotto();
    }

    @Test
    void 로또의_당첨_정보를_넣으면_결과_정보를_반환한다() {
        PrizeGroupingMap prizeGroupingMap = new PrizeGroupingMap(
                new HashMap<>() {{
                    put(LottoPrize.FIFTH_PRIZE, 1L);
                    put(LottoPrize.NONE, 4L);
                }}
        );

        assertThat(prizeGroupingMap.getProfit()).isEqualTo(1);
    }
}
