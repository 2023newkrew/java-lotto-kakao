package lotto;

import lotto.domain.DefaultRankingPolicy;
import lotto.domain.LottoGame;
import lotto.domain.RankingPolicy;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    InputView inputView = new InputView();
    RankingPolicy rankingPolicy = new DefaultRankingPolicy();
    OutputView outputView = new OutputView();
    LottoGame lottoGame;

    public AppConfig(){
        this.lottoGame = new LottoGame(rankingPolicy);
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
