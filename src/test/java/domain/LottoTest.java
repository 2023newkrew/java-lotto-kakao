package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 같은_위치의_같은_번호는_일치한다(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 1);
        MatchStatus matchStatus = lottoNumber1.match(lottoNumber2);
        Assertions.assertEquals(matchStatus, MatchStatus.MATCH);
    }

    @Test
    void 같은_위치의_다른_번호는_일치하지_않는다(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 2);
        MatchStatus matchStatus = lottoNumber1.match(lottoNumber2);
        Assertions.assertEquals(matchStatus, MatchStatus.NON_MATCH);
    }

    @Test
    void 보너스_볼은_번호가_같으면_일치한다(){
        LottoNumber lottoNumber = new LottoNumber(0, 1);
        BonusNumber bonusNumber = new BonusNumber(1);
        MatchStatus matchStatus = lottoNumber.match(bonusNumber);
        Assertions.assertEquals(matchStatus, MatchStatus.MATCH);
    }

    @Test
    void 보너스_볼은_번호가_다르면_일치하지_않는다(){
        LottoNumber lottoNumber = new LottoNumber(0, 1);
        BonusNumber bonusNumber = new BonusNumber(2);
        MatchStatus matchStatus = lottoNumber.match(bonusNumber);
        Assertions.assertEquals(matchStatus, MatchStatus.NON_MATCH);
    }
}


