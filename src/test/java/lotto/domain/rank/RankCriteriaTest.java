package lotto.domain.rank;

import static lotto.domain.rank.IsBonusRequired.TRUE;
import static lotto.domain.rank.IsBonusRequired.FALSE;
import static lotto.domain.rank.IsBonusRequired.IRRELEVANT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankCriteriaTest {
    @DisplayName("criteria의 isBonusRequired가 TRUE인 경우 정상적으로 동작한다.")
    @Test
    void rankCriteriaTrueTest() {
        RankCriteria criteria = new RankCriteria(3, 3, TRUE);

        assertThat(criteria.isSatisfiedBy(3, true)).isTrue();
        assertThat(criteria.isSatisfiedBy(3, false)).isFalse();
        assertThat(criteria.isSatisfiedBy(0, true)).isFalse();
    }

    @DisplayName("criteria의 isBonusRequired가 FALSE인 경우 정상적으로 동작한다.")
    @Test
    void rankCriteriaFalseTest() {
        RankCriteria criteria = new RankCriteria(3, 3, FALSE);

        assertThat(criteria.isSatisfiedBy(3, true)).isFalse();
        assertThat(criteria.isSatisfiedBy(3, false)).isTrue();
        assertThat(criteria.isSatisfiedBy(0, true)).isFalse();
    }

    @DisplayName("criteria의 isBonusRequired가 IRRELEVANT인 경우 정상적으로 동작한다.")
    @Test
    void rankCriteriaIrrelevantTest() {
        RankCriteria criteria = new RankCriteria(3, 3, IRRELEVANT);

        assertThat(criteria.isSatisfiedBy(3, true)).isTrue();
        assertThat(criteria.isSatisfiedBy(3, false)).isTrue();
        assertThat(criteria.isSatisfiedBy(0, true)).isFalse();
    }
}
