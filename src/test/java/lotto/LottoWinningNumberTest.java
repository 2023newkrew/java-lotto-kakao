package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoWinningNumberTest {
    List<LottoBall> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ));
    }

    @DisplayName("로또 당첨 번호 생성")
    @Test
    void create_bonusball() {
        LottoBall bonusBall = new LottoBall(7);
        assertDoesNotThrow(() -> new LottoWinningNumber(lottoNumbers, bonusBall));
    }

    @DisplayName("로또 보너스 번호 중복되는 경우")
    @Test
    void bonusball_duplicate() {
        LottoBall bonusBall = new LottoBall(6);
        assertThrows(IllegalArgumentException.class, () -> new LottoWinningNumber(lottoNumbers, bonusBall));
    }

    @DisplayName("로또 번호 6개 일치")
    @Test
    void win1() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        )));

        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(Ranking.FIRST);
    }

    @DisplayName("로또 번호 5개 일치 + 보너스 번호 일치")
    @Test
    void win2() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(7)
        )));

        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(Ranking.SECOND);
    }

    @DisplayName("로또 번호 5개 일치")
    @Test
    void win3() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(8)
        )));

        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(Ranking.THIRD);
    }

    @DisplayName("로또 번호 4개 일치")
    @Test
    void win4() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(8),
                new LottoBall(9)
        )));

        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(Ranking.FOURTH);
    }

    @DisplayName("로또 번호 3개 일치")
    @Test
    void win5() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(8),
                new LottoBall(9),
                new LottoBall(10)
        )));

        Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
        assertThat(ranking).isEqualTo(Ranking.FIFTH);
    }
}
