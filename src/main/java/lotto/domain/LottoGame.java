package lotto.domain;

import java.util.List;
import lotto.dto.GameResultDto;
import lotto.generatepolicy.DefaultGeneratePolicy;
import lotto.generatepolicy.GeneratePolicy;
import lotto.rankingpolicy.RankingPolicy;

public class LottoGame {
    private final RankingPolicy rankingPolicy;
    private final GeneratePolicy generatePolicy;
    private int lottoCount;
    private LottoHandler lottoHandler;

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

    public GameResultDto getGameResultDto() {
        return new GameResultDto(rankCounts, lottoCount);
    }

    public LottoHandler getLottoHandler() {
        return lottoHandler;
    }
}
