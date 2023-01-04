package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMatchTest {
    @DisplayName("매칭 개수 확인 테스트")
    @Test
    void matchTest(){
        //given
        LottoMatch lottoMatch = new LottoMatch(3,true);
        //when
        //then
        Assertions.assertThat(lottoMatch).isEqualTo(new LottoMatch(3, true));
    }

}
