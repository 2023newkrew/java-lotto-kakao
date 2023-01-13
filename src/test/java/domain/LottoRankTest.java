package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @DisplayName("6개 일치하면, 1등")
    @Test
    void test1(){
        assertThat(LottoRank.from(new LottoMatchResult(6, false)))
                .isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("5개 일치하고, 보너스 볼이 일치하면 2등")
    @Test
    void test2(){
        assertThat(LottoRank.from(new LottoMatchResult(5, true)))
                .isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 일치하고, 보너스 볼이 일치하지 않았으면 3등")
    @Test
    void test3(){
        assertThat(LottoRank.from(new LottoMatchResult(5, false)))
                .isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("4개 일치하면 4등")
    @Test
    void test4(){
        assertThat(LottoRank.from(new LottoMatchResult(4, true)))
                .isEqualTo(LottoRank.FOURTH_BONUS);
        assertThat(LottoRank.from(new LottoMatchResult(4, false)))
                .isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등")
    @Test
    void test5(){
        assertThat(LottoRank.from(new LottoMatchResult(3, true)))
                .isEqualTo(LottoRank.FIFTH_BONUS);
        assertThat(LottoRank.from(new LottoMatchResult(3, false)))
                .isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("3개 미만 일치하는 경우 등수 없음.")
    @Test
    void test6(){
        assertThat(LottoRank.from(new LottoMatchResult(2, false)))
                .isEqualTo(LottoRank.DEFAULT);
        assertThat(LottoRank.from(new LottoMatchResult(2, true)))
                .isEqualTo(LottoRank.DEFAULT);

        assertThat(LottoRank.from(new LottoMatchResult(1, false)))
                .isEqualTo(LottoRank.DEFAULT);
        assertThat(LottoRank.from(new LottoMatchResult(1, true)))
                .isEqualTo(LottoRank.DEFAULT);

        assertThat(LottoRank.from(new LottoMatchResult(0, false)))
                .isEqualTo(LottoRank.DEFAULT);
        assertThat(LottoRank.from(new LottoMatchResult(0, true)))
                .isEqualTo(LottoRank.DEFAULT);
    }
}
