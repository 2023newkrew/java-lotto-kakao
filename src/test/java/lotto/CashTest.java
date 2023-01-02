package lotto;

import lotto.exception.InvalidCashValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CashTest {
    @ParameterizedTest
    @ValueSource(ints = {-1,-1000,-4000})
    void 마이너스_금액인_경우_예외(int value){
        assertThatThrownBy(()->{
            Cash cash = new Cash(value);
        }).isInstanceOf(InvalidCashValue.class);
    }
}
