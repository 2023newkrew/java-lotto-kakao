package lotto;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class Application {
    public static final int LOTTO_PRICE = 1000;

    public static void main(String[] args) {
        List<Lotto> lottos = Lotto.autoGenerateLottos(LottoView.inputInit() / LOTTO_PRICE);
        LottoView.printLottoList(lottos);
        WinningLotto winningLotto = LottoView.inputWinningLotto();
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        LottoView.printLottoList(lottoGame.getLottos());
        LottoView.printResult(lottoGame.getResult());
    }
}
