package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("로또를 생성한다")
    @Test
    void 로또를_생성한다() {
        assertThatCode(()-> new Lotto()).doesNotThrowAnyException();
    }


    @DisplayName("로또를 지정된 숫자로 생성한다")
    @Test
    void 로또를_지정된_숫자로_생성한다() {
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1,2,3,4,5,6));
        assertThatCode(()-> new Lotto(lottoNumbers)).doesNotThrowAnyException();
    }

}
