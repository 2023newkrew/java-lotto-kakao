package dto;

import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @Test
    void 보너스번호와_당첨번호는_중복될_수_없다() {
        // given
        Lotto lotto = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);

        // when, then
        assertThatThrownBy(() -> new WinningLotto((lotto), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
