package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {
    @DisplayName("Price가 같은 경우")
    @Test
    void equal() {
        Price price = new Price(0);
        assertThat(price).isEqualTo(new Price(0));
    }

    @DisplayName("Price가 다른 경우")
    @Test
    void not_equal() {
        Price price = new Price(0);
        assertThat(price).isNotEqualTo(new Price(1));
    }

    @DisplayName("금액 추가")
    @Test
    void add_price() {
        Price price = new Price(0);
        price.add(100);
        assertThat(price).isEqualTo(new Price(100));
    }

    @DisplayName("금액 비율 계산")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1000 | 1000 | 1",
            "2000 | 1000 | 0.5",
            "1000 | 2000 | 2"
    })
    void scale(int income, int outcome, float expectedScale) {
        assertThat(new Price(income).scale(new Price(outcome))).isEqualTo(expectedScale);
    }
}
