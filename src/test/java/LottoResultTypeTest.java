import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;
import domain.lotto.result.LottoResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoResultTypeTest {

    @Test
    @DisplayName("로또 꽝 당첨 테스트")
    void checkLottoFail() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(6, 7, 8, 9, 10, 11));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 45), 7);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.FAIL);
    }

    @Test
    @DisplayName("로또 5등 당첨 테스트")
    void checkLotto5thPlace() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 43, 44, 45), 7);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.FIFTH_PLACE);
    }

    @Test
    @DisplayName("로또 4등 당첨 테스트")
    void checkLotto4thPlace() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(1, 2, 3, 4, 9, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 45), 7);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.FOURTH_PLACE);
    }

    @Test
    @DisplayName("로또 3등 당첨 테스트")
    void checkLotto3rdPlace() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 45), 42);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.THIRD_PLACE);
    }

    @Test
    @DisplayName("로또 2등 당첨 테스트")
    void checkLotto2ndPlace() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 45), 7);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.SECOND_PLACE);
    }

    @Test
    @DisplayName("로또 1등 당첨 테스트")
    void checkLotto1stPlace() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.create(()->List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7), 12);

        // when
        LottoResultType lottoResult = LottoResultType.getLottoResult(lottoNumbers, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResultType.FIRST_PLACE);
    }
}
