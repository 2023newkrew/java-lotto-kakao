package lottoTest.model;

import lotto.model.LottoRank;
import lotto.model.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @ParameterizedTest
    @EnumSource(LottoRank.class)
    @DisplayName("LottoResult에 등수 추가 테스트")
    void addLottoRankTest(LottoRank lottoRank) {
        //given
        LottoResult lottoResult = new LottoResult();

        //when
        lottoResult.addLottoRankCount(lottoRank);

        //then
        assertThat(lottoResult.getRankCountMap().get(lottoRank)).isEqualTo(1);
    }

}
