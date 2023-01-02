package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
