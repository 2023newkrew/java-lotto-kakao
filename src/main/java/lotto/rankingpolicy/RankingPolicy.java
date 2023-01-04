package lotto.rankingpolicy;

import lotto.domain.LottoRank;
import lotto.domain.lottoresult.LottoResult;

public interface RankingPolicy {
    LottoRank grade(LottoResult lottoResult);
}
