package lotto.domain;

import lotto.domain.exception.InvalidCashValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CashTest {
    @ParameterizedTest
    @ValueSource(longs = {-1,-1000,-4000})
    void 마이너스_금액인_경우_예외(long value){
        assertThatThrownBy(()->{
            Cash cash = new Cash(value);
        }).isInstanceOf(InvalidCashValue.class);
    }
    @ParameterizedTest
    @ValueSource(longs = {1,1000,40000000})
    void 정상_금액인_경우_정상실행(long value){
        assertThatCode(()->{
            Cash cash = new Cash(value);
        }).doesNotThrowAnyException();
    }
    @Test
    void 금액끼리_더할_수_있어야_한다(){
        long val1=1000;
        long val2=2000;
        Cash cash1 = new Cash(val1);
        Cash cash2 = new Cash(val2);
        Cash cash3 = cash1.plus(val2);

        Assertions.assertThat(cash3.getCashValue()).isEqualTo(3000);
    }

    @Test
    void toString의_경우_단위를_붙여야_한다(){
        long val1=1000;
        Cash cash1 = new Cash(val1);

        Assertions.assertThat(cash1.toString()).isEqualTo("1000원");
    }
}
