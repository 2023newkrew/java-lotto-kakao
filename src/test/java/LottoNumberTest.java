import exception.LottoNumberException;
import model.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    void 로또_번호는_1이상_45이하의_정수이다() {
        for (int i = 1; i <= 45; i++)
            Assertions.assertTrue(LottoNumber.getLottoNumbers().contains(LottoNumber.getLottoNumber(1)));
    }

    @Test
    void 로또_번호가_1미만일_경우_예외가_발생해야_한다() {
        Assertions.assertThrows(LottoNumberException.class, () -> LottoNumber.getLottoNumber(-1));
    }

    @Test
    void 로또_번호가_45초과일_경우_예외가_발생해야_한다() {
        Assertions.assertThrows(LottoNumberException.class, () -> LottoNumber.getLottoNumber(46));
    }
}
