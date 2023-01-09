package lotto.controller;

import lotto.model.*;
import lotto.factory.AutoLottosFactory;
import lotto.factory.LottosFactory;
import lotto.factory.ManualLottosFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGame lottoGame = new LottoGame();
    private final LottosFactory maunalLottosFactory = new ManualLottosFactory();
    private final LottosFactory autoLottosFactory = new AutoLottosFactory();

    public void start() {
        int money = inputView.receiveMoneyUserInput();
        int numberOfLotto = money / 1000;
        Lottos manualLottos = maunalLottosFactory.generate(numberOfLotto);
        Lottos autoLottos = autoLottosFactory.generate(numberOfLotto - manualLottos.getLottos().size());
        Lottos lottos = lottoGame.joinLottos(manualLottos, autoLottos);

        outputView.printLottos(lottos, manualLottos.getLottos().size(), autoLottos.getLottos().size());

        WinningNumbers winningNumbers = inputView.receiveLastLottoNumbers();
        RankingResult rankingResult = lottoGame.rankingCount(lottos.getRankings(winningNumbers));

        outputView.printStatistic(rankingResult);
        outputView.printRevenue(lottoGame.revenue(rankingResult, money));
    }
}
