package lotto.controller;

import lotto.model.Lottos;
import lotto.model.enums.Ranking;
import lotto.model.Winner;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Winner winner = new Winner();
    public void start(){
        int money = inputView.receiveMoneyUserInput();
        int numberOfLotto = money / 1000;
        Lottos lottos = new Lottos(numberOfLotto);

        outputView.printLottos(lottos);

        List<Integer> winNumbers = inputView.receiveLastLottoNumbers();
        int bonusNumber = inputView.receiveLastLottoBonusNumber(winNumbers);

        List<Ranking> rankings = lottos.getRankings(winNumbers, bonusNumber);
        Map<Ranking, Integer> rankingResult = winner.rankingCount(rankings);

        outputView.printStatistic(rankingResult);
        outputView.printRevenue(winner.revenue(rankingResult, money));
    }
}
