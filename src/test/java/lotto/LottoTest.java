package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoRandom;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.service.LottoCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class LottoTest {

    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest() {
        LottoRandom lottoRandom = new LottoRandom();
        ArrayList<Integer> lottoNumbers = lottoRandom.createRandomNumbers(); // 자동으로 생성되는 로또 번호들

        // 숫자 중복이 없는지 확인
        Set<Integer> dupCheck = new HashSet<Integer>();
        for (int num : lottoNumbers) {
            assertThat(dupCheck.contains(num)).isFalse();
            dupCheck.add(num);
        }
    }

    @RepeatedTest(20)
    @DisplayName("랜덤 숫자가 1~45 사이에 존재해야만 한다.")
    void lottoNumberRangeTest() {
        LottoRandom lottoRandom = new LottoRandom();
        LottoTicket lottoTicket = new LottoTicket(lottoRandom.createRandomNumbers());
        for (int i = 0; i < 6; i++) { // stream 으로 수정 필요
            Assertions.assertThat(lottoTicket.getLottoNumbers().get(i)).
                    isGreaterThanOrEqualTo(1).
                    isLessThanOrEqualTo(45);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {14000, 15300, 17800})
    @DisplayName("입력된 금액에 맞게 로또 티켓이 생성되어야 한다.")
    void lottoBuyTest(int cost){
        LottoController lottoController = new LottoController(cost);
        assertThat(lottoController.getLottoTicketCount()).isEqualTo(cost/1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    @DisplayName("1000원 미만의 금액이 들어온다면 예외를 발생한다.")
    void lottoLowerThan1000Test(int amount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTickets(amount));
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 계산해야 한다.")
    void lottoCorrectNumberTest(){
        // 당첨 번호
        LottoTicket winTicket = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));

        // 사용자가 뽑은 로또 번호들
        LottoTicket userTicket1 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 9, 10, 11)));
        LottoTicket userTicket2 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 9, 11)));
        LottoTicket userTicket3 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 11)));

        LottoCalculator lottoCalculator = new LottoCalculator(winTicket);

        assertThat(lottoCalculator.checkSameCount(userTicket1)).isEqualTo(3);
        assertThat(lottoCalculator.checkSameCount(userTicket2)).isEqualTo(4);
        assertThat(lottoCalculator.checkSameCount(userTicket3)).isEqualTo(5);
    }
}