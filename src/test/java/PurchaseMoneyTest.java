import exception.PurchaseMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static utils.Parser.parsingStringToPurchaseMoney;

public class PurchaseMoneyTest {
    @Test
    void 로또의_구입금액이_숫자가_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingStringToPurchaseMoney("abcd")).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 로또의_구입금액이_1000의_배수가_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> parsingStringToPurchaseMoney("1234")).isInstanceOf(PurchaseMoneyException.class);
    }
}
