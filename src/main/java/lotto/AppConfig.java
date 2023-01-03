package lotto;

import lotto.controller.LottoController;
import lotto.rankingpolicy.DefaultRankingPolicy;
import lotto.domain.LottoGame;
import lotto.rankingpolicy.RankingPolicy;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    private static final InputView inputView = new InputView();
    private static final RankingPolicy rankingPolicy = new DefaultRankingPolicy();
    private static final OutputView outputView = new OutputView();
    private final LottoGame lottoGame;
    private final LottoController lottoController;

    public AppConfig() {
        this.lottoGame = new LottoGame(rankingPolicy);
        this.lottoController = new LottoController(inputView, outputView, lottoGame);
    }

    public LottoController getLottoController() {
        return lottoController;
    }

    public LottoGame getLottoGame() {
        return lottoGame;
    }

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public RankingPolicy getRankingPolicy() {
        return rankingPolicy;
    }
}
