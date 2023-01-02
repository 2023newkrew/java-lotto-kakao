package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class LottoTest {

    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest(){
        // 6개의 숫자 중 중복이 없어야 한다.
        LottoController lotto = new LottoController();

        LottoTicket lottoTicket = lotto.createNumber(); // 자동으로 생성되는 로또 번호들
        List<Integer> lottoNumbers = lottoTicket.getLottoNumbers();

        // 숫자 중복이 없는지 확인
        Set<Integer> dupCheck = new HashSet<Integer>();
        for(int num : lottoNumbers){
            assertThat(dupCheck.contains(num)).isFalse();
            dupCheck.add(num);
        }
    }
}
