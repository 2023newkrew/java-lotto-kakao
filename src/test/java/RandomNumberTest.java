import domain.lotto.generator.LottoNumberGenerator;
import domain.lotto.ticket.LottoNumber;
import domain.lotto.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RandomNumberTest {

    @Test
    @DisplayName("랜덤 숫자를 생성하는 기능")
    void generateRandomNumberTest() {
        // given
        LottoNumberGenerator lottoNumber = new LottoNumberGenerator();
        List<LottoNumber> lottoNumberList =
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        List<LottoNumber> lottoNumbers = lottoNumber.makeNumbers(() -> new ArrayList<>(lottoNumberList));

        // when
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // then
        assertThat(lottoTicket.getLottoNumbers()).hasSize(6);
    }
}
