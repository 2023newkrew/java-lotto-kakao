import exception.ManualLottoCountException;
import model.LottoGame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static utils.Parser.parsingStringToInt;
import static utils.Parser.parsingStringToPurchaseMoney;

public class LottoGameTest {
    @Test
    void 수동으로_구입하는_로또의_개수가_숫자가_아닐경우_예외가_발생한다() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.setPurchaseMoney(parsingStringToPurchaseMoney("10000"));
        assertThatThrownBy(() -> lottoGame.setManualLottoCount(parsingStringToInt("abc"))).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 수동으로_구입하는_로또의_개수가_0_미만이거나_구입_개수를_초과하면_예외가_발생한다() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.setPurchaseMoney(parsingStringToPurchaseMoney("10000"));
        assertThatThrownBy(() -> lottoGame.setManualLottoCount(parsingStringToInt("99"))).isInstanceOf(ManualLottoCountException.class);
    }
}
