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
}
