package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("같은 위치의 같은 번호는 일치한다.")
    @Test
    void test(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 1);
        LottoMatchStatus matchStatus = lottoNumber1.match(lottoNumber2);
        Assertions.assertEquals(matchStatus, LottoMatchStatus.MATCH);
    }

    @DisplayName("같은 위치의 다른 번호는 일치하지 않는다.")
    @Test
    void test1(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 2);
        LottoMatchStatus matchStatus = lottoNumber1.match(lottoNumber2);
        Assertions.assertNotEquals(matchStatus, LottoMatchStatus.MATCH);
    }
}


