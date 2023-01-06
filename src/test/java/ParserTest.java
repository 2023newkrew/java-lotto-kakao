import exception.ManualLottoCountException;
import exception.PurchaseMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static utils.Parser.parsingManualLottoCount;
import static utils.Parser.parsingPurchaseMoney;

public class ParserTest {

    @Test
    void 로또의_구입금액이_숫자가_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingPurchaseMoney("abcd")).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 로또의_구입금액이_1000의_배수가_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingPurchaseMoney("1234")).isInstanceOf(PurchaseMoneyException.class);
    }

    @Test
    void 수동으로_구입하는_로또의_개수가_숫자가_아닐경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingManualLottoCount("abcd", 10)).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 수동으로_구입하는_로또의_개수가_0_미만이거나_구입_개수를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> parsingManualLottoCount("5", 3)).isInstanceOf(ManualLottoCountException.class);
    }
}
