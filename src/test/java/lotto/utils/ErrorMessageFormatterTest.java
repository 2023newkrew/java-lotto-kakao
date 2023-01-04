package lotto.utils;

import static lotto.utils.ErrorMessageFormatter.makeErrorMessage;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ErrorMessageFormatterTest {

    @ParameterizedTest
    @ValueSource(strings = {"12", "a"})
    @DisplayName("ErrorMessage는 포맷에 의해 생성된다.")
    void ErrorMessage는_포맷에_의해_생성된다(String input) {
        Assertions.assertThat(makeErrorMessage("a", input, "test")).isEqualTo(String.format("EXPECTED: a\n"
                + "ACTUAL: %s\n"
                + "CONTEXT: test\n", input));

    }

}