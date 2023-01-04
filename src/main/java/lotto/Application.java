package lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.WinningLotto;

import static lotto.view.LottoView.*;
import static lotto.domain.Lotto.LOTTO_PRICE;

public class Application {
    public static void main(String[] args) {
        int lottoAmount = inputTotalMoney() / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(Lotto.manualGenerateByNumbersList(inputManualLottoNumbers(inputManualLottoAmount())));
        lottos.addAll(Lotto.autoGenerateByAmounts(lottoAmount - lottos.size()));
        printLottoList(lottos);
        WinningLotto winningLotto = inputWinningLotto();
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        printLottoList(lottoGame.getLottos());
        printResult(lottoGame.getResult());
    }
}
