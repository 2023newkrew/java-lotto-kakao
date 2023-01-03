import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberParserTest {

    @Test
    void String을_받으면_콤마와_띄어쓰기로_구분한다() {
        NumberParser numberParser = new NumberParser();
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> numbers = numberParser.splitAndParse(input);
        assertThat(numbers).isEqualTo(result);

    }

    @Test
    void 파싱한_결과는_숫자만_가능하다() {
        NumberParser numberParser = new NumberParser();
        String input = "1";

        assertThat(numberParser.parse(input)).isEqualTo(1);
    }

    @Test
    void 파싱이_불가능할_경우_예외가_발생한다() {
        NumberParser numberParser = new NumberParser();
        String input = "ab";

        assertThatThrownBy(() -> numberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
