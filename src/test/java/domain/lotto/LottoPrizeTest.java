package domain.lotto;

import domain.lotto.prize.LottoPrize;
import domain.lotto.ticket.LottoTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class LottoPrizeTest {

    LottoWinningNumber lottoWinningNumber;
    LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        LottoNumber bonusNumber = new LottoNumber(10);
        lottoWinningNumber = new LottoWinningNumber(lottoNumbers, bonusNumber);
    }

    @DisplayName("다 맞으면 1등이다.")
    @Test
    void firstPrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.FIRST_PRIZE);
    }

    @DisplayName("5개 맞고 보너스 숫자 맞으면 2들이다.")
    @Test
    void secondPrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(10)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.SECOND_PRIZE);
    }

    @DisplayName("5개 맞으면 3등이다.")
    @Test
    void thirdPrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(41)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.THIRD_PRIZE);
    }

    @DisplayName("4개 맞으면 4등이다.")
    @Test
    void fourthPrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(41),
                new LottoNumber(42)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.FOURTH_PRIZE);
    }

    @DisplayName("3개 맞으면 5등이다.")
    @Test
    void fifthPrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(41),
                new LottoNumber(42),
                new LottoNumber(43)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.FIFTH_PRIZE);
    }

    @DisplayName("2개 이하로 맞으면 꽝이다.")
    @Test
    void nonePrize() {
        lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(40),
                new LottoNumber(41),
                new LottoNumber(42),
                new LottoNumber(43)
        ));

        LottoPrize prize = LottoPrize.findPrize(lottoTicket, lottoWinningNumber);
        Assertions.assertThat(prize)
                .isEqualTo(LottoPrize.NONE_PRIZE);
    }

}
