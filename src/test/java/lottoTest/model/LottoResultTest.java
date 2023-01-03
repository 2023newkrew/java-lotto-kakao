package lottoTest.model;

import lotto.model.LottoRank;
import lotto.model.LottoResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @ParameterizedTest
    @EnumSource(LottoRank.class)
    void addLottoRankTest(LottoRank lottoRank){
        //given
        LottoResult lottoResult = new LottoResult();

        //when
        lottoResult.addLottoRank(lottoRank);

        //then
        assertThat(lottoResult.getRankCountMap().get(lottoRank)).isEqualTo(1);
    }

}
