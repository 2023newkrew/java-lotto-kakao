package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Winner winner = new Winner();
    private final LottosFactory maunalLottosFactory = new ManualLottosFactory();
    private final LottosFactory autoLottosFactory = new AutoLottosFactory();

    public void start() {
        int money = inputView.receiveMoneyUserInput();
        int numberOfLotto = money / 1000;
        Lottos manualLottos = maunalLottosFactory.generate(numberOfLotto);
        Lottos autoLottos = autoLottosFactory.generate(numberOfLotto - manualLottos.getLottos().size());
        Lottos lottos = maunalLottosFactory.joinLottos(manualLottos, autoLottos);

        outputView.printLottos(lottos, manualLottos.getLottos().size(), autoLottos.getLottos().size());

        WinningNumbers winningNumbers = inputView.receiveLastLottoNumbers();
        RankingResult rankingResult = winner.rankingCount(lottos.getRankings(winningNumbers));

        outputView.printStatistic(rankingResult);
        outputView.printRevenue(winner.revenue(rankingResult, money));
    }
}
