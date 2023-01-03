package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class LottoAnalyzerTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        @DisplayName("당첨 번호가 없을 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        void should_throwException_when_winningNumberIsNull(WinningNumber winningNumber) {
            Assertions.assertThatThrownBy(() -> LottoAnalyzer.create(winningNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("당첨 번호가 없습니다.");
        }
    }
}