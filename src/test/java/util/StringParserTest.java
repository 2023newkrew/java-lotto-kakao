package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class StringParserTest {
    @DisplayName("파싱 테스트")
    @Test
    void parseTest(){
        //given
        //when
        //then
        Assertions.assertThat(StringParser.parse("1, 2, 3, 4, 5, 6")).isEqualTo(List.of(1,2,3,4,5,6));
    }
}
