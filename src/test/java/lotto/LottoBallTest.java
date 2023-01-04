package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallTest {
    @DisplayName("1~45사이의 정수인 경우")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1",
            "45",
    })
    void validBall(int value) {
        new LottoBall(value);
    }

    @DisplayName("1~45사이의 정수가 아닌 경우")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "" + Integer.MIN_VALUE,
            "-1",
            "0",
            "46",
            "" + Integer.MAX_VALUE,
    })
    void invalidBall(int value) {
        assertThatThrownBy(() -> new LottoBall(value));
    }


    @DisplayName("공 동등성 비교")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1      | 1     | true",
            "1      | 2     | false",
            "44     | 45    | false",
            "45     | 45    | true",
    })
    void ballEquality(int a, int b, boolean equality) {
        assertThat(new LottoBall(a).equals(new LottoBall(b)))
                .isEqualTo(equality);
    }

    @DisplayName("공 순서 비교")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "1      | 2         | -1",
            "2      | 2         | 0",
            "2      | 1         | 1",
    })
    void ballEquality(int a, int b, int expectedValue) {
        assertThat(new LottoBall(a).compareTo(new LottoBall(b))).isEqualTo(expectedValue);
    }

    @DisplayName("올바른 문자열 형태의 공 파싱")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1'      | 1 ",

    })
    void validBall(String ballLit, int ballNum) {
        assertThat(LottoBall.parse(ballLit))
                .isEqualTo(new LottoBall(ballNum));
    }

    @DisplayName("올바르지 않은 문자열 형태의 공 파싱")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'---'",
    })
    void invalidBall(String ballLit) {
        assertThatThrownBy(() -> LottoBall.parse(ballLit));
    }
}
