package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberTest {
    @Test
    void 같은_위치의_같은_번호는_일치한다(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 1);
        assertThat(lottoNumber1.match(lottoNumber2)).isTrue();
    }

    @Test
    void 같은_위치의_다른_번호는_일치하지_않는다(){
        LottoNumber lottoNumber1 = new LottoNumber(0, 1);
        LottoNumber lottoNumber2 = new LottoNumber(0, 2);
        assertThat(lottoNumber1.match(lottoNumber2)).isFalse();
    }

    @Test
    void 보너스_볼은_번호가_같으면_일치한다(){
        LottoNumber lottoNumber = new LottoNumber(0, 1);
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThat(lottoNumber.match(bonusNumber)).isTrue();
    }

    @Test
    void 보너스_볼은_번호가_다르면_일치하지_않는다(){
        LottoNumber lottoNumber = new LottoNumber(0, 1);
        BonusNumber bonusNumber = new BonusNumber(2);
        assertThat(lottoNumber.match(bonusNumber)).isFalse();
    }
}


