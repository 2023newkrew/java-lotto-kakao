package lotto;

import lotto.domain.*;
import lotto.service.LottoCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    private LottoWinnerTicket winnerTicket;
    private LottoTicket userTicket1;
    private LottoTicket userTicket2;
    private LottoTicket userTicket3;
    private LottoTicket userTicket4;
    private LottoTicket userBonusTicket;

    @BeforeEach
    public void setUp(){
        List<Integer> winnerList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> userTicketList1 = List.of(1, 2, 3, 8, 9, 11);
        List<Integer> userTicketList2 = List.of(1, 2, 3, 4, 9, 12);
        List<Integer> userTicketList3 = List.of(1, 2, 3, 4, 5, 13);
        List<Integer> userTicketList4 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> userBonusList = List.of(1, 2, 3, 4, 5, 11);

        this.winnerTicket =  new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(winnerList.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()))),
                new LottoNumber(11));
        this.userTicket1 = new LottoTicket(new ArrayList<>(userTicketList1.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
        this.userTicket2 = new LottoTicket(new ArrayList<>(userTicketList2.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
        this.userTicket3 = new LottoTicket(new ArrayList<>(userTicketList3.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
        this.userTicket4 = new LottoTicket(new ArrayList<>(userTicketList4.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
        this.userBonusTicket = new LottoTicket(new ArrayList<>(userBonusList.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 계산해야 한다.")
    void lottoCorrectNumberTest() {
        WinnerScore winnerScore = new WinnerScore();
        Assertions.assertThat(winnerScore.checkSameCount(userTicket1, this.winnerTicket)).isEqualTo(3);
        Assertions.assertThat(winnerScore.checkSameCount(userTicket2, this.winnerTicket)).isEqualTo(4);
        Assertions.assertThat(winnerScore.checkSameCount(userTicket3, this.winnerTicket)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 볼과 일치하는 볼이 있는지 확인할 수 있어야 한다.")
    void lottoBonusCheckTest() {
        WinnerScore winnerScore = new WinnerScore();
        Assertions.assertThat(winnerScore.isBonusNumber(userTicket1, this.winnerTicket)).isTrue();
        Assertions.assertThat(winnerScore.isBonusNumber(userTicket2, this.winnerTicket)).isFalse();
        Assertions.assertThat(winnerScore.isBonusNumber(userTicket3, this.winnerTicket)).isFalse();
    }


    @Test
    @DisplayName("당첨금액의 합을 알 수 있어야 한다.")
    void lottoWinningAmountTest() {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);
        WinnerScore winScore = new WinnerScore();
        LottoTickets lottoTickets = new LottoTickets(4000);

        lottoTickets.registerManualTicket(userTicket1);
        lottoTickets.registerManualTicket(userTicket2);
        lottoTickets.registerManualTicket(userTicket4);
        lottoTickets.registerManualTicket(userTicket4);

        System.out.println(lottoTickets);
        winScore.addScore(lottoTickets, winnerTicket);
        long summary = lottoCalculator.getWinSummary(winScore);
        assertThat(summary).isEqualTo(4000055000L);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 10500, 10900})
    @DisplayName("수익률을 계산해야 한다.")
    void lottoRateOfReturnTest(int amount) {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);

        LottoTickets lottoTickets = new LottoTickets(amount);
        lottoTickets.registerManualTicket(userBonusTicket);

        WinnerScore winScore = lottoCalculator.getWinScore(lottoTickets);
        assertThat(lottoCalculator.calcRateOfReturn(winScore,amount)).isEqualTo(3000);
    }
}