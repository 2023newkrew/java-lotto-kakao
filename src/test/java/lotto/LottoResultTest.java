package lotto;

import lotto.domain.*;
import lotto.util.LottoPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


public class LottoResultTest {
    @Test
    @DisplayName("1등 당첨 테스트")
    void 일등당첨() {
        Lotto myLotto = new Lotto(List.of(1,2,3,4,5,6));
        Lotto winningLotto = new Lotto(List.of(1,2,3,4,5,6));
        LottoNumber bonusNumber = new LottoNumber(7);

        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("2등 당첨 테스트")
    void 이등당첨() {
        Lotto myLotto = new Lotto(List.of(1,2,3,4,5,7));
        Lotto winningLotto = new Lotto(List.of(1,2,3,4,5,6));
        LottoNumber bonusNumber = new LottoNumber(7);

        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("5등 당첨 테스트")
    void 오등당첨() {
        Lotto myLotto = new Lotto(List.of(1,2,3,8,9,10));
        Lotto winningLotto = new Lotto(List.of(1,2,3,4,5,6));
        LottoNumber bonusNumber = new LottoNumber(7);

        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("여러개의 로또 당첨시 당첨 개수 제공")
    void 일등한개이등한개오등한개개수() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);

        assertThat(stat.getByRank(LottoRank.FIRST)).isEqualTo(1);
        assertThat(stat.getByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(stat.getByRank(LottoRank.THIRD)).isEqualTo(0);
        assertThat(stat.getByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(stat.getByRank(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("상금 일치 테스트")
    void 일등한개이등한개오등한개상금() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);
        int totalPrize = (LottoRank.FIRST.PRIZE + LottoRank.SECOND.PRIZE + LottoRank.FIFTH.PRIZE);
        assertThat(stat.getPrizeAmount()).isEqualTo(totalPrize);
    }

    @Test
    @DisplayName("수익률 일치 테스트")
    void profitRateTest() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);
        int totalPrize = (LottoRank.FIRST.PRIZE + LottoRank.SECOND.PRIZE + LottoRank.FIFTH.PRIZE);
        double profitRate = totalPrize / (double) (3 * LottoPayment.LOTTO_COST);
        assertThat(stat.getProfitRate()).isCloseTo(profitRate, within(0.0001));
    }
}
