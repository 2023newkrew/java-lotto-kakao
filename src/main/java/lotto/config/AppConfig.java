package lotto.config;

import lotto.controller.LottoController;
import lotto.generatepolicy.DefaultGeneratePolicy;
import lotto.generatepolicy.GeneratePolicy;
import lotto.rankingpolicy.DefaultRankingPolicy;
import lotto.domain.LottoGame;
import lotto.rankingpolicy.RankingPolicy;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    private static final InputView inputView = new InputView();
    private static final RankingPolicy rankingPolicy = new DefaultRankingPolicy();
    private static final OutputView outputView = new OutputView();

    private static final GeneratePolicy generatePolicy = new DefaultGeneratePolicy();
    private final LottoController lottoController;

    public AppConfig() {
        LottoGame lottoGame = new LottoGame(rankingPolicy, generatePolicy);
        this.lottoController = new LottoController(inputView, outputView, lottoGame);
    }

    public LottoController getLottoController() {
        return lottoController;
    }
}
