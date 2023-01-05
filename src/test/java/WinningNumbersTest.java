import domain.lotto.WinningNumbers;
import domain.lotto.ticket.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {

    @Test
    @DisplayName("로또 용지를 생성하는 기능")
    void createLottoTicketTest(){
        List<LottoNumber> lottoNumberList =
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        WinningNumbers winningNumbers = new WinningNumbers(lottoNumberList, new LottoNumber(7));

        for(int i = 1; i < 7; i++){
            assertThat(lottoNumberList.get(i-1)).isEqualTo(new LottoNumber(i));
        }
        assertThat(winningNumbers.getBonusNumber()).isEqualTo(new LottoNumber(7));

    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호 중복 되지 않음")
    void checkDuplicateWinningNumberTest(){
        List<LottoNumber> lottoNumberList =
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(lottoNumberList, new LottoNumber(5))
                );
    }
}
