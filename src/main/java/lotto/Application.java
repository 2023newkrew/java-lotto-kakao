package lotto;

import lotto.domain.LottoStore;

import static lotto.view.LottoView.*;
import static lotto.domain.Lotto.LOTTO_PRICE;

public class Application {
    public static void main(String[] args) {
        int lottoAmount = inputTotalMoney() / LOTTO_PRICE;
        LottoStore lottoStore = new LottoStore();
        lottoStore.buyLottosByNumbers(inputManualLottoNumbers(inputManualLottoAmount()));
        lottoStore.buyRandomLottosByAmounts(lottoAmount - lottoStore.getLottoAmount());
        printLottoList(lottoStore.getLottos());
        lottoStore.setWinningLotto(inputWinningLotto());
        printResult(lottoStore.getLottosResult());
    }
}
