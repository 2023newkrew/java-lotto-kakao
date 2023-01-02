package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
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
}