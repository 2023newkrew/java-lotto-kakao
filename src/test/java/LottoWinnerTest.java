import exception.LottoNumberException;
import model.Lotto;
import model.LottoNumber;
import model.LottoWinner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoWinnerTest {
    @Test
    void 보너스_번호가_로또_번호와_중복될_경우_예외가_발생해야_한다() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getLottoNumber(number));
        }
        LottoNumber bonusNumber = LottoNumber.getLottoNumber(6);
        Assertions.assertThrows(LottoNumberException.class, () -> new LottoWinner(new Lotto(lottoNumbers), bonusNumber));
    }
}
