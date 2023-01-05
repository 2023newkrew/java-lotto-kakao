package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerLottoResultTest {

    @DisplayName("로또 당첨 결과를 추가할 수 있다")
    @Test
    void addResult() {
        //given
        PlayerLottoResult playerLottoResult = new PlayerLottoResult(1_000);

        //when
        playerLottoResult.addResult(LottoResult.NO_MATCH);

        //then
        double profitRate = playerLottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(0.0);
    }

    @DisplayName("로또 당첨 결과를 통해 수익률을 계산할 수 있다")
    @Test
    void calculateProfitRate() {
        //given
        PlayerLottoResult playerLottoResult = new PlayerLottoResult(1_000);

        //when
        playerLottoResult.addResult(LottoResult.NO_MATCH);
        playerLottoResult.addResult(LottoResult.FIFTH_PLACE);

        // then
        double profitRate = playerLottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(5.0);
    }
}
