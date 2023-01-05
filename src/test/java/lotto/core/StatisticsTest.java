package lotto.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsTest {
    @Test
    void valid() {
        List<LottoTicket> lotto = List.of(
                LottoTicket.parse("1,2,3,4,5,6"),
                LottoTicket.parse("2,3,4,5,6,7"),
                LottoTicket.parse("2,3,4,5,6,8"),
                LottoTicket.parse("3,4,5,6,8,9"),
                LottoTicket.parse("4,5,6,8,9,10"),
                LottoTicket.parse("5,6,8,9,10,11")
        );
        LottoWinningNumber winningNumber = new LottoWinningNumber(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ), new LottoBall(7));
        Statistics statistics = Statistics.fromLotto(lotto, winningNumber);
        assertThat(statistics.getOrDefault(Ranking.FIRST, -1)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Ranking.SECOND, -1)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Ranking.THIRD, -1)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Ranking.FOURTH, -1)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Ranking.FIFTH, -1)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Ranking.OTHER, -1)).isEqualTo(1);
    }
}
