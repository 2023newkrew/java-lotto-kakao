package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class LottoTest {

    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest(){
        LottoTicket lottoTicket = new LottoTicket(); // 자동으로 생성되는 로또 번호들
        ArrayList<Integer> lottoNumbers = lottoTicket.createRandomNumbers();

        // 숫자 중복이 없는지 확인
        Set<Integer> dupCheck = new HashSet<Integer>();
        for(int num : lottoNumbers){
            assertThat(dupCheck.contains(num)).isFalse();
            dupCheck.add(num);
        }
    }

    @RepeatedTest(20)
    @DisplayName("랜덤 숫자가 1~45 사이에 존재해야만 한다.")
    void lottoNumberRangeTest(){
        LottoTicket lottoTicket = new LottoTicket();
        lottoTicket.createRandomNumbers();
        for (int i=0; i<6; i++) { // stream 으로 수정 필요
            Assertions.assertThat(lottoTicket.getLottoNumbers().get(i)).
                    isGreaterThanOrEqualTo(1).
                    isLessThanOrEqualTo(45);
        }
    }
}