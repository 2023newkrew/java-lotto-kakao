package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StringParserTest {
    @DisplayName("파싱 테스트")
    @Test
    void parseTest() {
        assertThat(StringParser.parse("1, 2, 3, 4, 5, 6")).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("반점으로 구분되지 않는 input을 받으면 예외 발생")
    @Test
    void parseException() {
        assertThatIllegalArgumentException().isThrownBy(() -> StringParser.parse("1 2 3 4 5 6"));
    }
}
