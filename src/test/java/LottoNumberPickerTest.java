import domain.LottoNumberPicker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberPickerTest {
    @Test
    void 총_6개의_숫자로_이뤄진_리스트를_반환한다() {
        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        assertThat(lottoNumberPicker.makeLottoTicket().size()).isEqualTo(6);
    }
}
