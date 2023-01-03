package lotto.domain;

import java.util.List;
import lotto.generatepolicy.DefaultGeneratePolicy;
import lotto.generatepolicy.GeneratePolicy;

public class LottoGame {
    private RankingPolicy rankingPolicy;
    private LottoHandler lottoHandler;
    private int lottoCount;

    private GeneratePolicy generatePolicy;
    private LottoAnswer lottoAnswer;
    private List<Integer> rankCounts;

    public LottoGame(RankingPolicy rankingPolicy) {
        this(rankingPolicy, new DefaultGeneratePolicy());
    }

    public LottoGame(RankingPolicy rankingPolicy, GeneratePolicy generatePolicy) {
        this.rankingPolicy = rankingPolicy;
        this.generatePolicy = generatePolicy;
    }

    public void createLotto(int lottoCount) {
        this.lottoCount = lottoCount;
        lottoHandler = new LottoHandler(lottoCount, rankingPolicy, generatePolicy);
    }

    public void setLottoAnswer(LottoAnswer lottoAnswer) {
        this.lottoAnswer = lottoAnswer;
    }

    public void grade() {
        rankCounts = lottoHandler.grade(lottoAnswer);
    }

    public GameResult getGameResult() {
        return new GameResult(rankCounts, lottoCount);
    }

    public LottoHandler getLottoHandler() {
        return lottoHandler;
    }
}
