package lotto.rankingpolicy;

import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

public interface RankingPolicy {
    LottoRank grade(LottoResult lottoResult);
}
