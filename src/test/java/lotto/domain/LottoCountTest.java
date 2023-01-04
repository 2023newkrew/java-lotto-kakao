package lotto.domain;

import lotto.domain.exception.InvalidLottoCountValue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoCountTest {
    @ParameterizedTest
    @ValueSource(ints={-1,-100,-3})
    void 음수가_되면_안됨(int val){
        assertThatThrownBy(()->{
            LottoCount lottoCount = new LottoCount(val);
        }).isInstanceOf(InvalidLottoCountValue.class);
    }
    @ParameterizedTest
    @ValueSource(ints={1,100,0})
    void 양수혹은0이면_정상실행(int val){
        assertThatCode(()->{
            LottoCount lottoCount = new LottoCount(val);
        }).doesNotThrowAnyException();
    }
}