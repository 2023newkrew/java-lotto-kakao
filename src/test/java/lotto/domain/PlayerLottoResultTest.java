package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerLottoResultTest {
    @Test
    void 로또_당첨_결과를_추가할_수_있다() {
        //given
        PlayerLottoResult playerLottoResult = new PlayerLottoResult(1_000);

        //when
        playerLottoResult.addResult(LottoResult.NO_MATCH);

        //then
        double profitRate = playerLottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(0.0);
    }

    @Test
    void 로또_당첨_결과를_통해_수익률을_계산할_수_있다() {
        //given
        PlayerLottoResult playerLottoResult = new PlayerLottoResult(1_000);

        //when
        playerLottoResult.addResult(LottoResult.NO_MATCH);
        playerLottoResult.addResult(LottoResult.THREE_MATCH);

        // then
        double profitRate = playerLottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(5.0);
    }
}
