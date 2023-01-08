package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRanksTest {


    HashMap<LottoRank, Integer> ranks;

    @BeforeEach
    void init() {
        ranks = new HashMap<>();
        Arrays.stream(LottoRank.values()).forEach(rank -> ranks.put(rank, 0));


    }

    @Test
    @DisplayName("Lotto_결과를_포맷에_맞게_출력한다")
    void Lotto_결과를_포맷에_맞게_출력한다() {

        //given
        ranks.put(LottoRank.FIRST, 10);
        ranks.put(LottoRank.SECOND, 5);
        ranks.put(LottoRank.THIRD, 3);
        ranks.put(LottoRank.FOURTH, 2);
        ranks.put(LottoRank.FIFTH, 1);

        //when
        String resultString = LottoRanks.createLottoRanks(ranks).getResultString();

        //then
        Assertions.assertThat(resultString).isEqualTo(
                "3개 일치 (5000원)- 1개\n" + "4개 일치 (50000원)- 2개\n" + "5개 일치 (1500000원)- 3개\n"
                        + "5개 일치, 보너스 볼 일치(30000000원) - 5개\n" + "6개 일치 (2000000000원)- 10개\n"
                        + "총 수익률은 959743.10입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");

    }

}