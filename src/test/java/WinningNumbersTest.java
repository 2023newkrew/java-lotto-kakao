import domain.lotto.number.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호를 생성하는 기능")
    void createWinningNumbersTest(){
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
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,5), 5)
                );
    }

    @Test
    @DisplayName("당첨 번호, 보너스 번호 숫자 범위 예외 테스트")
    void checkWinningNumbersDomainTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(0,2,3,4,5,6), 7));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,46), 7));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,6), 0));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,6), 46));
    }
}
