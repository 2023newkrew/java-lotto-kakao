package lotto.controller;

import lotto.model.Lottos;
import lotto.model.LottosFactory;
import lotto.model.WinningNumbers;
import lotto.model.enums.RankingType;
import lotto.model.Winner;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Winner winner = new Winner();
    private final LottosFactory lottosFactory = new LottosFactory();
    public void start(){
        int money = inputView.receiveMoneyUserInput();
        int numberOfLotto = money / 1000;
        Lottos manualLottos = lottosFactory.makeLottosManual(inputView.receiveManualLottos(numberOfLotto));
        Lottos autoLottos = lottosFactory.makeLottosAuto(numberOfLotto - manualLottos.getLottos().size());
        Lottos lottos = lottosFactory.joinLottos(manualLottos, autoLottos);

        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = inputView.receiveLastLottoNumbers();

        List<RankingType> rankingTypes = lottos.getRankings(winningNumbers);
        Map<RankingType, Integer> rankingResult = winner.rankingCount(rankingTypes);

        outputView.printStatistic(rankingResult);
        outputView.printRevenue(winner.revenue(rankingResult, money));
    }
}
