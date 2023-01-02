import domain.LottoNumberPicker;
import domain.LottoTicket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberPickerTest {
    @Test
    void LottoTicket을_반환한다() {
        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        assertThat(lottoNumberPicker.makeLottoTicket())
                .isInstanceOf(LottoTicket.class);
    }
}
