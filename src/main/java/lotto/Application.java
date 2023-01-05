package lotto;

import lotto.domain.LottoStore;

import static lotto.view.LottoView.*;
import static lotto.view.LottoPrinter.*;

public class Application {
    public static void main(String[] args) {
        LottoStore lottoStore = new LottoStore(inputTotalMoney());
        lottoStore.buyLottosByNumbers(inputManualLottoNumbers(inputManualLottoAmount()));
        lottoStore.buyRandomLottosByMoneyLeft();
        printLottoList(lottoStore.getLottos());
        lottoStore.setWinningLotto(inputWinningLotto());
        printResult(lottoStore.getLottosResult());
    }
}
