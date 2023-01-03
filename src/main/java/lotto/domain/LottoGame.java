package lotto.domain;

import java.util.List;

public class LottoGame {
    private final RankingPolicy rankingPolicy;
    private LottoHandler lottoHandler;
    private int lottoCount;
    private LottoAnswer lottoAnswer;
    private List<Integer> rankCounts;

    public LottoGame(RankingPolicy rankingPolicy) {
        this.rankingPolicy = rankingPolicy;
    }

    public void createLotto(int lottoCount) {
        this.lottoCount = lottoCount;
        lottoHandler = new LottoHandler(lottoCount, rankingPolicy);
    }

    public void setLottoAnswer(LottoAnswer lottoAnswer) {
        this.lottoAnswer = lottoAnswer;
    }

    public void grade() {
        rankCounts = lottoHandler.grade(lottoAnswer);
    }

    public GameResultDto getGameResult() {
        return GameResultDto.of(new GameResult(rankCounts, lottoCount));
    }

    public LottoHandler getLottoHandler() {
        return lottoHandler;
    }
}
