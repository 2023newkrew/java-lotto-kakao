import model.Lotto;
import model.LottoNumber;
import model.LottoTicket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.LottoFactory.createLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {

    LottoNumber toLottoNumber(int number) {
        return LottoNumber.getLottoNumber(number);
    }

    Lotto toLotto(List<Integer> numberList) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numberList) {
            lottoNumbers.add(toLottoNumber(number));
        }
        return new Lotto(lottoNumbers);
    }

    @Test
    void 구입_개수_빼기_수동_로또_개수_만큼_자동_로또를_발급한다() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(toLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(toLotto(Arrays.asList(1, 5, 10, 20, 30, 40)));

        LottoTicket lottoTicket = createLottoTicket(3, lottos);

        assertThat(lottoTicket.getLottoList()).hasSize(5);
    }
}
