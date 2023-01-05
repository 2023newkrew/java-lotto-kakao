package domain.lotto;

import domain.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserAccountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1_000})
    @DisplayName("잔액은 0원 이상이다.")
    void validateRange(int value) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new UserAccount(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    @DisplayName("0원 미만의 금액이면 예외가 발생한다")
    void validateRange_throw(int value) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new UserAccount(value));
    }
}
