package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class WinningLottoTest {
    @DisplayName("당첨 번호에 포함된 숫자는 보너스 번호가 될 수 없다.")
    @Test
    void test(){
        StubLottoNumbersGenerator lottoTicketGenerator = new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(6);

        Assertions.assertThatThrownBy(() -> new WinningLotto(lottoTicketGenerator.generate(), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
