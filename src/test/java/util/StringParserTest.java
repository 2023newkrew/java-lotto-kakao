package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringParserTest {
    @DisplayName("파싱 테스트")
    @Test
    void parseTest(){
        assertThat(StringParser.parse("1, 2, 3, 4, 5, 6")).isEqualTo(List.of(1,2,3,4,5,6));
    }
}
