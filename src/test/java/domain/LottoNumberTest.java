package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberTest {

    @DisplayName("같은 번호는 일치한다")
    @Test
    void test1(){
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);
        assertThat(lottoNumber1.equals(lottoNumber2)).isTrue();
    }

    @DisplayName("다른 번호는 일치하지 않는다")
    @Test
    void test2(){
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(2);
        assertThat(lottoNumber1.equals(lottoNumber2)).isFalse();
    }

    @DisplayName("로또 번호는 1 이상 45이하의 값만 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    void test3(int number){
        Assertions.assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}


