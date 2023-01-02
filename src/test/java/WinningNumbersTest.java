import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {

    @Test
    @DisplayName("로또 용지를 생성하는 기능")
    void createLottoTicketTest(){
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6), 7);

        assertThat(winningNumbers.getLottoNumber()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningNumbers.getBonusNumber()).isEqualTo(7);

    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호 중복 되지 않음")
    void checkDuplicateWinningNumberTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,6), 5)
                );
    }
}
