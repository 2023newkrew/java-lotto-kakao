package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {
    @DisplayName("가격의 동등성 확인")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1000 | 1000 | true",
            "1000 | 2000 | false",
    })
    void equality(long a, long b, boolean expectedEquality) {
        Price priceA = new Price(a);
        Price priceB = new Price(b);
        assertThat(priceA.equals(priceB)).isEqualTo(expectedEquality);
    }

    @DisplayName("금액 추가")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1000 | 1000 | 2000",
            "1000 | 2000 | 3000",
    })
    void add(long a, long b, long expectedResult) {
        Price priceA = new Price(a);
        Price priceB = new Price(b);
        assertThat(priceA.add(priceB)).isEqualTo(new Price(expectedResult));
    }

    @DisplayName("금액 비율 계산")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1000 | 1000 | 1 | 1",
            "2000 | 1000 | 2 | 1",
            "1000 | 2000 | 1 | 2"
    })
    void ratio(long income, long outcome, int numerator, int denominator) {
        assertThat(new Price(income).ratio(new Price(outcome)))
                .isEqualTo(new Ratio<>(numerator, denominator));
    }


    @DisplayName("나머지를 무시하는 나누기 연산을 확인합니다.(floor divide)")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1000 | 1000 | 1",
            "1500 | 1000 | 1",
            "2000 | 1000 | 2"
    })
    void floorDivide(int valueA, int valueB, int expectedValue) {
        assertThat(new Price(valueA).floorDivide(new Price(valueB))).isEqualTo(expectedValue);
    }
}
