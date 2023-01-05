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
import java.util.stream.Stream;

import static lotto.utils.Constants.WINNER_SCORE_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    private LottoWinnerTicket winnerTicket;
    private LottoTicket userTicket1;
    private LottoTicket userTicket2;
    private LottoTicket userTicket3;
    private LottoTicket userBonusTicket;

    @BeforeEach
    public void setUp(){
        List<Integer> winnerList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> userTicketList1 = List.of(1, 2, 3, 8, 9, 11);
        List<Integer> userTicketList2 = List.of(1, 2, 3, 4, 9, 12);
        List<Integer> userTicketList3 = List.of(1, 2, 3, 4, 5, 13);
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
        this.userBonusTicket = new LottoTicket(new ArrayList<>(userBonusList.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 계산해야 한다.")
    void lottoCorrectNumberTest() {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);
        assertThat(lottoCalculator.checkSameCount(userTicket1)).isEqualTo(3);
        assertThat(lottoCalculator.checkSameCount(userTicket2)).isEqualTo(4);
        assertThat(lottoCalculator.checkSameCount(userTicket3)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 볼과 일치하는 볼이 있는지 확인할 수 있어야 한다.")
    void lottoBonusCheckTest() {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);
        Assertions.assertThat(lottoCalculator.isBonusNumber(userTicket1)).isTrue();
        Assertions.assertThat(lottoCalculator.isBonusNumber(userTicket2)).isFalse();
        Assertions.assertThat(lottoCalculator.isBonusNumber(userTicket3)).isFalse();
    }


    @ParameterizedTest
    @ValueSource(strings = {"0,0,0,6,0"})
    @DisplayName("당첨금액의 합을 알 수 있어야 한다.")
    void lottoWinningAmountTest(String userScore) {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);

        WinnerScore winScore = new WinnerScore();
        List<Integer> scoreList = List.of(convertToArray(userScore));
        for (int idx=0; idx < WINNER_SCORE_COUNT; idx++) {
            for (int count = 0; count < scoreList.get(idx); count++) {
                winScore.addScore(idx);
            }
        }
        long summary = lottoCalculator.getWinSummary(winScore);
        assertThat(summary).isEqualTo(12000000000L);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 10500, 10900})
    @DisplayName("수익률을 계산해야 한다.")
    void lottoRateOfReturnTest(int amount) {
        LottoCalculator lottoCalculator = new LottoCalculator(winnerTicket);

        LottoTickets lottoTickets = new LottoTickets(amount);
        lottoTickets.createManualTicket(userBonusTicket);

        WinnerScore winScore = lottoCalculator.getWinScore(lottoTickets);
        assertThat(lottoCalculator.calcRateOfReturn(winScore,amount)).isEqualTo(3000);
    }

    private Integer[] convertToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}