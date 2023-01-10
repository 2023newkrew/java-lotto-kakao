package domain.lotto;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoWinningNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoWinningNumberTest {

    @DisplayName("당첨 번호 6개와 보너스 번호 1개를 주입받아서 생성한다")
    @Test
    void create() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)

        ));
        LottoNumber bonusNumber = new LottoNumber(10);

        Assertions.assertThatNoException()
                .isThrownBy(() -> new LottoWinningNumber(lottoNumbers, bonusNumber));
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void generateLottoWinningNumber() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        LottoNumber bonusNumber = new LottoNumber(10);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoWinningNumber(lottoNumbers, bonusNumber));
    }

    @Test
    @DisplayName("생성된 로또 번호 중 중복이 있으면 예외가 발생한다")
    void generateLottoWinningNumber_throw() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(1), //duplicate
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        LottoNumber bonusNumber = new LottoNumber(10);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoWinningNumber(lottoNumbers, bonusNumber));
    }
}
