import exception.LottoNumberException;
import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTest {
    @Test
    void 로또_번호가_6개가_아닐_경우_예외가_발생해야_한다() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getLottoNumber(number));
        }

        Assertions.assertThrows(LottoNumberException.class, () -> new Lotto(lottoNumbers));
    }

    @Test
    void 로또_번호에_중복이_있을_경우_예외가_발생해야_한다() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getLottoNumber(number));
        }
        Assertions.assertThrows(LottoNumberException.class, () -> new Lotto(lottoNumbers));
    }
}
