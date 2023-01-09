package lotto;

import lotto.controller.LottoGame;
import lotto.domain.LottoRandom;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class LottoTest {

    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest() {
        LottoRandom lottoRandom = new LottoRandom();
        List<Integer> lottoNumbers = lottoRandom.createRandomNumbers(); // 자동으로 생성되는 로또 번호들

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
        LottoGame lottoGame = new LottoGame(cost, new ArrayList<>());
        assertThat(lottoGame.getLottoTickets().getLottoTicketSize()).isEqualTo(cost/1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    @DisplayName("1000원 미만의 금액이 들어온다면 예외를 발생한다.")
    void lottoLowerThan1000Test(int amount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTickets(amount, new ArrayList<>()));
    }
}