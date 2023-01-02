package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RatioTest {
    @DisplayName("불가능한 비율을 확인합니다.")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {

            "  | 1",
            "1 |  ",
            "  | ",
            "0 | 0",
    })
    void invalidRatio(Integer numerator, Integer denominator) {
        assertThatThrownBy(() -> new Ratio<>(numerator, denominator));
    }

    @DisplayName("가능한 비율을 확인합니다..")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {

            "1 | 1",
            "1 | 0",
            "0 | 1",
    })
    void validRatio(Integer numerator, Integer denominator) {
        Ratio<Integer> ratio = new Ratio<>(numerator, denominator);
        assertThat(ratio.getNumerator()).isEqualTo(numerator);
        assertThat(ratio.getDenominator()).isEqualTo(denominator);
    }

    @DisplayName("비율이 동일한 경우를 확인합니다.")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1 | 1 | 1 | 1",
            "1 | 1 | 2 | 2",
            "1 | 2 | 2 | 4",
            "2 | 4 | 1 | 2",
    })
    void equalRatio(int numeratorA, int denominatorA, int numeratorB, int denominatorB) {
        Ratio<Integer> ratioA = new Ratio<>(numeratorA, denominatorA);
        Ratio<Integer> ratioB = new Ratio<>(numeratorB, denominatorB);
        assertThat(ratioA).isEqualTo(ratioB);
    }

    @DisplayName("비율이 다른 경우를 확인합니다.")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1 | 1 | 1 | 2",
            "1 | 2 | 3 | 4",
    })
    void notEqualRatio(int numeratorA, int denominatorA, int numeratorB, int denominatorB) {
        Ratio<Integer> ratioA = new Ratio<>(numeratorA, denominatorA);
        Ratio<Integer> ratioB = new Ratio<>(numeratorB, denominatorB);
        assertThat(ratioA).isNotEqualTo(ratioB);
    }

}
