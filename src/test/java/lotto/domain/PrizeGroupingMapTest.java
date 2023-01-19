package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PrizeGroupingMapTest {

    @Test
    void 로또의_당첨_정보를_넣으면_결과_정보를_반환한다() {
        PrizeGroupingMap prizeGroupingMap = new PrizeGroupingMap(
                new HashMap<>() {{
                    put(LottoPrize.FIFTH_PRIZE, List.of());
                    put(LottoPrize.NONE, List.of());
                }}
        );
        String expected = "6개 일치 (2000000000원) - 0개\n"
                + "5개 일치, 보너스 볼 일치 (30000000원) - 0개\n"
                + "5개 일치 (1500000원) - 0개\n"
                + "4개 일치 (50000원) - 0개\n"
                + "3개 일치 (5000원) - 1개\n"
                + "0개 일치 (0원) - 13개\n"
                + "총 수익률은 0.36입니다.\n";

        assertThat(prizeGroupingMap.toString()).isEqualTo(expected);
    }
}
