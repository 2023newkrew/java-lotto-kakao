package lotto;

import lotto.exception.InvalidLottoNumberValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints={-1,-100,-3})
    void 음수가_되면_안됨(int val){
        assertThatThrownBy(()->{
            LottoNumber lottoNumber = new LottoNumber(val);
        }).isInstanceOf(InvalidLottoNumberValue.class);
    }
    @ParameterizedTest
    @ValueSource(ints={1,100,0})
    void 양수혹은0이면_정상실행(int val){
        assertThatCode(()->{
            LottoNumber lottoNumber = new LottoNumber(val);
        }).doesNotThrowAnyException();
    }
}