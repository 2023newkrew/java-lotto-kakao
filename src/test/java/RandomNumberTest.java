import domain.lotto.number.LottoNumberMaker;
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
        LottoNumberMaker lottoNumberMaker = new LottoNumberMaker();
        List<Integer> lottoNumbers = lottoNumberMaker.makeNumbers(() -> new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));

        // when
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // then
        assertThat(lottoTicket.getLottoNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
