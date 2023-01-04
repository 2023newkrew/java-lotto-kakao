package lotto;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

import static lotto.domain.Lotto.LOTTO_PRICE;

public class Application {

    public static void main(String[] args) {
        List<Lotto> lottos = Lotto.autoGenerateLottos(LottoView.inputInit() / LOTTO_PRICE);
        LottoView.printLottoList(lottos);
        WinningLotto winningLotto = LottoView.inputWinningLotto();
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        LottoView.printLottoList(lottoGame.getLottos());
        LottoView.printResult(lottoGame.getResult());
    }
}
