import exception.LottoNumberCountException;
import exception.LottoNumberDuplicateException;
import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static utils.Parser.parsingStringToLotto;

public class LottoTest {

    @Test
    void 로또_번호에_중복이_있을_경우_예외가_발생해야_한다() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getLottoNumber(number));
        }
        Assertions.assertThrows(LottoNumberDuplicateException.class, () -> new Lotto(lottoNumbers));
    }

    @Test
    void 입력되는_로또_번호가_정수가_아닐경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingStringToLotto("a,b,3,4,5,6")).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 로또_번호가_6개가_아닐_경우_예외가_발생해야_한다() {
        assertThatThrownBy(() -> parsingStringToLotto("1,2,3,4,5")).isInstanceOf(LottoNumberCountException.class);
    }
}
