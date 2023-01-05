package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.LottoResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("matchCount와 bonusBallMatch를 활용해 알맞은 LottoResult를 반환받을 수 있다. - 1등")
    @Test
    void findFirstPlace() {
        // given
        int matchCount = 6;
        boolean bonusBallMatch = false;

        // when
        LottoResult result = LottoResult.findResult(matchCount, bonusBallMatch);

        // then
        assertThat(result).isEqualTo(FIRST_PLACE);
    }

    @DisplayName("matchCount와 bonusBallMatch를 활용해 알맞은 LottoResult를 반환받을 수 있다. - 2등")
    @Test
    void findSecondPlace() {
        // given
        int matchCount = 5;
        boolean bonusBallMatch = true;

        // when
        LottoResult result = LottoResult.findResult(matchCount, bonusBallMatch);

        // then
        assertThat(result).isEqualTo(SECOND_PLACE);
    }

    @DisplayName("matchCount와 bonusBallMatch를 활용해 알맞은 LottoResult를 반환받을 수 있다. - 3등")
    @Test
    void findthirdPlace() {
        // given
        int matchCount = 5;
        boolean bonusBallMatch = false;

        // when
        LottoResult result = LottoResult.findResult(matchCount, bonusBallMatch);

        // then
        assertThat(result).isEqualTo(THIRD_PLACE);
    }

    @DisplayName("matchCount와 bonusBallMatch를 활용해 알맞은 LottoResult를 반환받을 수 있다. - 등수 없음")
    @Test
    void findNoMatch() {
        // given
        int matchCount = 1;
        boolean bonusBallMatch = true;

        // when
        LottoResult result = LottoResult.findResult(matchCount, bonusBallMatch);

        // then
        assertThat(result).isEqualTo(NO_MATCH);
    }
}