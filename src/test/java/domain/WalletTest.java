package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WalletTest {
    @DisplayName("지갑은 양의 정수의 돈을 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void test1(int value){
        Assertions.assertThatThrownBy(() -> new Wallet(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지갑에 들어있는 돈을 초과하는 돈을 사용할 수 없다.")
    @Test
    void test2(){
        Wallet wallet = new Wallet(1000);
        Assertions.assertThatThrownBy(() -> wallet.use(1001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음수의 돈을 사용할 수 없다.")
    @Test
    void test3(){
        Wallet wallet = new Wallet(1000);
        Assertions.assertThatThrownBy(() -> wallet.use(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
