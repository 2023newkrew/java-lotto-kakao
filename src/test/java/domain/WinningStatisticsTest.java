package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatisticsTest {
    @DisplayName("FIFTH와 FIFTH_BONUS는 같은 등수로 취급한다.")
    @Test
    void test1(){
        List<LottoMatchResult> lottoMatchResults = List.of(
                new LottoMatchResult(3, false)
                , new LottoMatchResult(3, true));

        assertThat(new WinningStatistics(new GameResult(lottoMatchResults), 0)
                .getRankCount(LottoRank.FIFTH)).isEqualTo(2);
    }

    @DisplayName("FOURTH와 FOURTH_BONUS는 같은 등수로 취급한다.")
    @Test
    void test2(){
        List<LottoMatchResult> lottoMatchResults = List.of(
                new LottoMatchResult(4, false)
                , new LottoMatchResult(4, true)
        );

        assertThat(new WinningStatistics(new GameResult(lottoMatchResults), 0)
                .getRankCount(LottoRank.FOURTH)).isEqualTo(2);
    }

    @DisplayName("당첨 수익은 21억 이상의 값을 가질 수 있다.")
    @Test
    void test3(){
        List<LottoMatchResult> lottoMatchResults = List.of(
                new LottoMatchResult(6, false),
                new LottoMatchResult(6, false)
        );

        assertThat(new WinningStatistics(new GameResult(lottoMatchResults), 0)
                .getProfit()).isEqualTo(((long) 2000000000 * 2));
    }

    @DisplayName("당첨 수익은 당첨된 내역을 통해 계산된다.")
    @Test
    void test4(){
        List<LottoMatchResult> lottoMatchResults = List.of(
                new LottoMatchResult(6, false),
                new LottoMatchResult(6, false),

                new LottoMatchResult(5, true),
                new LottoMatchResult(5, true),

                new LottoMatchResult(5, false),
                new LottoMatchResult(5, false),

                new LottoMatchResult(4, true),
                new LottoMatchResult(4, false),

                new LottoMatchResult(3, true),
                new LottoMatchResult(3, false),

                new LottoMatchResult(2, true),
                new LottoMatchResult(2, false),
                new LottoMatchResult(1, true),
                new LottoMatchResult(1, false),
                new LottoMatchResult(0, true),
                new LottoMatchResult(0, false)
        );

        assertThat(new WinningStatistics(new GameResult(lottoMatchResults), 0)
                .getProfit()).isEqualTo(
                 ((long) 2000000000 * 2)
                                + (30000000 * 2)
                                + (1500000 * 2)
                                + (50000 * 2)
                                + (5000 * 2));
    }

    @DisplayName("수익률은 수익 / 사용 금액으로 계산된다.")
    @Test
    void test5(){
        List<LottoMatchResult> lottoMatchResults = List.of(
                new LottoMatchResult(6, false),
                new LottoMatchResult(5, true),
                new LottoMatchResult(5, false),
                new LottoMatchResult(4, false),
                new LottoMatchResult(3, true)
        );

        assertThat(new WinningStatistics(new GameResult(lottoMatchResults), 1000)
                .getRateOfReturn()).isEqualTo(
                 ((double) 2000000000 + 30000000 + 1500000 + 50000 + 5000) / 1000);
    }
}
