package lotto.exception;

import static org.junit.jupiter.api.Assertions.*;

import net.bytebuddy.build.HashCodeAndEqualsPlugin.Sorted;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ErrorMessageFormatterTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void errorMessageTest(int input) {
        String errorMessage = ErrorMessageFormatter.makeErrorMessage("a", input, "context");
        Assertions.assertThat(errorMessage).isEqualTo(String.format("EXPECTED: a\n"
                + "ACTUAL: %d\n"
                + "CONTEXT: context\n", input));
    }


}