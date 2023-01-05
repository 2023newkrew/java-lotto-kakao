package lotto.domain;

import static lotto.domain.LottoResult.makeResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    @DisplayName("LottoResult는_matchCount와_hasBonus로_생성이_가능하다")
    void LottoResult는_matchCount와_hasBonus로_생성이_가능하다() {
        Assertions.assertThatCode(() -> makeResult(5, false)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("LottoResult로부터_LottoRank를_얻을_수_있다")
    void LottoResult로부터_LottoRank를_얻을_수_있다() {
        assertThat(makeResult(6, false).getRank()).isSameAs(LottoRank.FIRST);
        assertThat(makeResult(5, true).getRank()).isSameAs(LottoRank.SECOND);
        assertThat(makeResult(5, false).getRank()).isSameAs(LottoRank.THIRD);
        assertThat(makeResult(4, false).getRank()).isSameAs(LottoRank.FOURTH);
        assertThat(makeResult(3, false).getRank()).isSameAs(LottoRank.FIFTH);
        assertThat(makeResult(2, false).getRank()).isSameAs(LottoRank.NOTHING);
    }
    @Test
    @DisplayName("LottoResult의_matchCount가_6이고_hasBonus가_true이면_에러가_발생한다")
    void LottoResult의_matchCount가_6이고_hasBonus가_true이면_에러가_발생한다() {
        assertThatThrownBy(() -> makeResult(6, true)).isInstanceOf(IllegalArgumentException.class);
    }
}