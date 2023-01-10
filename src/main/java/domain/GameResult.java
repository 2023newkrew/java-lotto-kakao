package domain;

import java.util.List;

public class GameResult {
    private final List<LottoMatchResult> lottoMatchResults;

    public GameResult(List<LottoMatchResult> lottoMatchResults) {
        this.lottoMatchResults = lottoMatchResults;
    }

    public List<LottoMatchResult> getLottoMatchResults() {
        return lottoMatchResults;
    }
}
