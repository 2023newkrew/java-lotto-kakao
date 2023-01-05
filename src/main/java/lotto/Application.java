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
        LottoView lottoView = new LottoView();
        List<Lotto> lottos = lottoGenerator.generateLottos(lottoView.receivePurchasePrice());
        lottoView.printLottos(lottos);
        WinningLotto winningLotto = lottoView.receiveWinningLotto();
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        lottoView.printResult(lottoGame.getResult());
    }
}
