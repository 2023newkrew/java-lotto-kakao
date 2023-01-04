package lotto;

import lotto.domain.*;
import lotto.domain.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class LottoResultTest {
    @Test
    @DisplayName("로또결과 일치하는 번호가 몇개인지, 보너스번호가 일치하는지 반환한다")
    void lottoResult1() {
        Lotto myLotto = new Lotto(1,2,3,4,5,6);
        Lotto winLotto = new Lotto(3,4,5,6,7,8);
        LottoNumber bonus = new LottoNumber(1);
        assertThat(new LottoResult(myLotto, winLotto, bonus)).isEqualTo(new LottoResult(4, true));
    }

    @Test
    @DisplayName("6개 일치하면 1등 당첨이다.")
    void lottoResult2() {
        assertThat(new LottoResult(6, true).getRank()).isEqualTo(LottoRank.FIRST);
        assertThat(new LottoResult(6, false).getRank()).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("5개 일치, 보너스 일치하면 2등 당첨이다.")
    void lottoResult3() {
        assertThat(new LottoResult(5, true).getRank()).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("2개 이하 일치하면 꽝이다")
    void lottoResult4() {
        assertThat(new LottoResult(2, true).getRank()).isEqualTo(LottoRank.FAIL);
    }
}
