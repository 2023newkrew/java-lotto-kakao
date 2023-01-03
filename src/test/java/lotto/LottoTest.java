package lotto;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    @Test
    @DisplayName("로또 당첨번호가 몇개 인지 반환")
    void iAmRich() {
        LottoNumber myNumber = new LottoNumber(List.of(1,2,3,4,5,6));
        LottoNumber answer = new LottoNumber(List.of(4,5,6,7,8,9));
        assertThat(myNumber.compare(answer)).isEqualTo(3);
    }
}
