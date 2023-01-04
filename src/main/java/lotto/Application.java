package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoGenerator;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class Application {

    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottos = lottoGenerator.generateLottos(LottoView.receivePurchasePrice());
        LottoView.printLottos(lottos);
        WinningLotto winningLotto = LottoView.receiveWinningLotto();
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        LottoView.printResult(lottoGame.getResult());
    }
}
