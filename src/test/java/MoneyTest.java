import domain.Money;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000, -500})
    void 돈은_0원_이상이다_실패(final int invalidAmount) {
        assertThatCode(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 0원 이상이여야 합니다.");
    }
}
