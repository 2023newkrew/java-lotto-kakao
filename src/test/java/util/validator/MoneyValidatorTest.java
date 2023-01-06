package util.validator;

import domain.Money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyValidatorTest {
    @DisplayName("로또 구입 금액이 범위 이내인지 테스트")
    @Test
    void validateInRangeTest() {
        int biggerThanIntegerMax = (Integer.MAX_VALUE + 1);

        assertThatIllegalArgumentException().isThrownBy(() -> MoneyValidator.validate(biggerThanIntegerMax));
        assertThatCode(() -> MoneyValidator.validate(1))
                .doesNotThrowAnyException();
    }
}
