import domain.lotto.number.LottoNumber;
import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;
import exception.BonusNumberDuplicationException;
import exception.LottoNumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호를 생성하는 기능")
    void createWinningNumbersTest() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(winningNumbers.getMatchNumberSize(LottoNumbers.create(() -> List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(6);

        assertThat(winningNumbers.checkHasBonusNumber(LottoNumbers.create(() -> List.of(2, 3, 4, 5, 6, 7)))).isTrue();
        assertThat(winningNumbers.checkHasBonusNumber(LottoNumbers.create(() -> List.of(7, 8, 9, 10, 11, 12)))).isTrue();
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호 중복 되지 않음")
    void checkDuplicateWinningNumberTest() {
        assertThatExceptionOfType(BonusNumberDuplicationException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 5)
                );
        assertThatExceptionOfType(BonusNumberDuplicationException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5), 5)
                );
    }

    @Test
    @DisplayName("당첨 번호, 보너스 번호 숫자 범위 예외 테스트")
    void checkWinningNumbersDomainTest() {
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(0, 2, 3, 4, 5, 6), 7));
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46), 7));
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 0));
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46));
    }

    @Test
    @DisplayName("로또 번호의 일치하는 숫자 개수")
    public void validateLottoNumbersDuplication() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 45);
        LottoNumbers lottoNumbers1 = LottoNumbers.create(() -> List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers lottoNumbers2 = LottoNumbers.create(() -> List.of(11, 12, 13, 14, 15, 16));
        LottoNumbers lottoNumbers3 = LottoNumbers.create(() -> List.of(1, 2, 3, 14, 15, 16));

        // when
        int matchNumber1 = winningNumbers.getMatchNumberSize(lottoNumbers1);
        int matchNumber2 = winningNumbers.getMatchNumberSize(lottoNumbers2);
        int matchNumber3 = winningNumbers.getMatchNumberSize(lottoNumbers3);

        // then
        assertThat(matchNumber1).isEqualTo(6);
        assertThat(matchNumber2).isEqualTo(0);
        assertThat(matchNumber3).isEqualTo(3);
    }
}
