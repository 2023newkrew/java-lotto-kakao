package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinningNumberTest {
    private static final Map<Integer, Ranking> RANK_TABLE = Map.of(
            1, Ranking.FIRST,
            2, Ranking.SECOND,
            3, Ranking.THIRD,
            4, Ranking.FOURTH,
            5, Ranking.FIFTH,
            6, Ranking.OTHER
    );

    @DisplayName("올바른 로또 번호 생성")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1,2,3,4,5,6'   | 7",
    })
    void validWinningNumber(String balls, int bonusBall) {
        new LottoWinningNumber(
                Arrays.stream(balls.split(","))
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList()),
                new LottoBall(bonusBall)
        );
    }

    @DisplayName("올바르지 않은 로또 번호 생성")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1,2,3,4,5,6'   | 6",
            "'1,2,3,4,5,5'   | 7",
    })
    void invalidWinningNumber(String balls, int bonusBall) {
        assertThatThrownBy(() -> new LottoWinningNumber(
                Arrays.stream(balls.split(","))
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList()),
                new LottoBall(bonusBall)
        ));
    }

    @DisplayName("로또 결과 검증")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1,2,3,4,5,6'   | 7 | '1,2,3,4,5,6'        | 1",
            "'1,2,3,4,5,6'   | 7 | '1,2,3,4,5,7'        | 2",
            "'1,2,3,4,5,6'   | 7 | '1,2,3,4,5,8'        | 3",
            "'1,2,3,4,5,6'   | 7 | '1,2,3,4,9,8'        | 4",
            "'1,2,3,4,5,6'   | 7 | '1,2,3,10,9,8'       | 5",
            "'1,2,3,4,5,6'   | 7 | '1,2,11,10,9,8'      | 6",
            "'1,2,3,4,5,6'   | 7 | '1,12,11,10,9,8'     | 6",
            "'1,2,3,4,5,6'   | 7 | '13,12,11,10,9,8'    | 6",
    })
    void rank(String winnerBalls, int bonusBall, String userBalls, int rank) {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(
                Arrays.stream(winnerBalls.split(","))
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList()),
                new LottoBall(bonusBall)
        );
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.stream(userBalls.split(","))
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList())
        );
        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(RANK_TABLE.get(rank));
    }
}
