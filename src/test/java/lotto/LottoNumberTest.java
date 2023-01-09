package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("1~45인 수로 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25, 45})
    void lottoNumberTest_valid(int number) {
        //given
        //when
        //then
        Assertions.assertThatNoException().isThrownBy(() -> LottoNumber.valueOf(number));
    }

    @DisplayName("0, 45이상인 수로 생성시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 47, 48})
    void lottoNumberTest_invalid(int number) {
        //given
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> LottoNumber.valueOf(number));
    }

    @DisplayName("같은 숫자인 LottoNumbers는 같은 객체임을 확인")
    @Test
    void lottoNumberInstanceTest() {
        //given
        LottoNumber lottoNumber1 = LottoNumber.valueOf(10);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(10);
        //when
        //then
        Assertions.assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
