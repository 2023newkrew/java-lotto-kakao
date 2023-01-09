package lotto;

import lotto.domain.Customer;
import lotto.domain.LottoGame;
import lotto.domain.LottoGenerator;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class LottoController {

    private final LottoView lottoView;
    private final LottoGenerator lottoGenerator;

    public LottoController(LottoView lottoView, LottoGenerator lottoGenerator) {
        this.lottoView = lottoView;
        this.lottoGenerator = lottoGenerator;
    }

    public void start() {
        Customer customer = new Customer(lottoView.receivePurchasePrice());
        customer.registerManualLottos(lottoView.receiveManualLottos());
        customer.registerAutoLottos(lottoGenerator.generateLottos(customer.getTicketCount()));
        lottoView.printLottos(customer);
        WinningLotto winningLotto = lottoView.receiveWinningLotto();
        LottoGame lottoGame = new LottoGame(customer, winningLotto);
        lottoView.printResult(lottoGame.getGameResult());
    }

}
