package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberParserTest {

    @Test
    void String을_받으면_콤마와_띄어쓰기로_구분한다() {
        // given
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<Integer> numbers = NumberParser.splitAndParse(input);

        // then
        assertThat(numbers).isEqualTo(result);

    }

    @Test
    void 파싱한_결과는_숫자만_가능하다() {
        assertThat(NumberParser.parse("1")).isEqualTo(1);
        assertThat(NumberParser.parse("5")).isEqualTo(5);
        assertThat(NumberParser.parse("10")).isEqualTo(10);
    }

    @ValueSource(strings = {"abcd", "!@"})
    @NullAndEmptySource
    @ParameterizedTest
    void 파싱이_불가능할_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> NumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
