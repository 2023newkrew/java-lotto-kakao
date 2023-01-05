package lotto;

import lotto.util.LottoParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParserTest {
    @Test
    @DisplayName("문자열을 콤마(,) 기준으로 나눈 숫자 배열을 반환한다.")
    void parsingLottoString() {
        String string = "1, 2, 3, 4, 5, 6";
        assertThat(LottoParser.getNumbers(string)).contains(1,2,3,4,5,6) ;
    }

    @Test
    @DisplayName("숫자, 콤마, space 이외의 문자가 존재하면 에러를 던진다.")
    void parseErrorTest() {
        String string = "1,2,3,4=5:6";
        assertThatThrownBy(()->LottoParser.getNumbers(string)).isInstanceOf(RuntimeException.class);
    }
}
