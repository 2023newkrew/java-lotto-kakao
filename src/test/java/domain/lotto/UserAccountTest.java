package domain.lotto;

import lotto.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class UserAccountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1_000})
    @DisplayName("잔액은 0원 이상이다.")
    void validateRange(int value) {
        assertThatNoException()
                .isThrownBy(() -> new UserAccount(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    @DisplayName("0원 미만의 금액이면 예외가 발생한다")
    void validateRange_throw(int value) {
        assertThatIllegalStateException()
                .isThrownBy(() -> new UserAccount(value));
    }

    @Test
    void withdraw() {
        UserAccount userAccount = new UserAccount(10000);
        userAccount.withdraw(1000);
        assertThat(userAccount.getBalance()).isEqualTo(9000);
    }

    @Test
    void withdraw_exception() {
        UserAccount userAccount = new UserAccount(0);

        assertThatIllegalStateException()
                .isThrownBy(() -> userAccount.withdraw(1000));
    }
}
