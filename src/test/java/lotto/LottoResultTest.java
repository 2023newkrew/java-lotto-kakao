package lotto;

import lotto.domain.*;
import lotto.util.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class LottoResultTest {
    @Test
    @DisplayName("로또 일등 당첨은 여섯개의 번호 일치시 이십억의 상금")
    void 일등당첨() {
        Lotto myLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(7);

        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(LottoRank.FIRST);
    }
    
    @Test
    @DisplayName("로또 2등 당첨은 5개의 번호와 1개의 보너스 일치시 삼천만원의 상금")
    void 이등당첨() {
        Lotto myLotto = new Lotto(Stream.of(1,2,3,4,5,7).map(LottoNumber::new).collect(Collectors.toList()));
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(7);

        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("로또 5등 당첨은 3개의 번호 일치시 오천원의 상금")
    void 오등당첨() {
        Lotto myLotto = new Lotto(Stream.of(1,2,3,8,9,10).map(LottoNumber::new).collect(Collectors.toList()));
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
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
    @DisplayName("1등:1, 2등:1, 5등:1의 당첨금액은 20억 3천만 5천원이다.")
    void 일등한개이등한개오등한개상금() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);

        // 1등 20억, 2등 3천만, 3등 150만, 4등 5만, 5등 5천
        assertThat(stat.getPrizeAmount()).isEqualTo(2_030_005_000);
    }

    @Test
    @DisplayName("1등:1, 2등:1, 5등:1의 수익률은 676668.3333333334이다.")
    void name() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);
        assertThat(stat.getProfitRate()).isEqualTo(676668.3333333334);
    }
}
