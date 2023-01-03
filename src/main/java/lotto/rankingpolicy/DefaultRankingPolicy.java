package lotto.rankingpolicy;

import static lotto.domain.LottoRank.*;
import static lotto.domain.LottoRank.SECOND;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.LottoRank;
import lotto.domain.DefaultLottoResult;
import lotto.domain.LottoResult;

public class DefaultRankingPolicy implements RankingPolicy {

    private Map<DefaultLottoResult, LottoRank> lottoResultLottoRankMap = new HashMap<>();

    public DefaultRankingPolicy() {
        lottoResultLottoRankMap.put(new DefaultLottoResult(FIRST.getMatchCount(), FIRST.isRequiredBonus()), FIRST);
        lottoResultLottoRankMap.put(new DefaultLottoResult(SECOND.getMatchCount(), SECOND.isRequiredBonus()), SECOND);
        lottoResultLottoRankMap.put(new DefaultLottoResult(THIRD.getMatchCount(), THIRD.isRequiredBonus()), THIRD);
        lottoResultLottoRankMap.put(new DefaultLottoResult(FOURTH.getMatchCount(), FOURTH.isRequiredBonus()), FOURTH);
        lottoResultLottoRankMap.put(new DefaultLottoResult(FIFTH.getMatchCount(), FIFTH.isRequiredBonus()), FIFTH);
        lottoResultLottoRankMap.put(new DefaultLottoResult(THIRD.getMatchCount(), THIRD.isRequiredBonus()), THIRD);
        lottoResultLottoRankMap.put(new DefaultLottoResult(FOURTH.getMatchCount(), FOURTH.isRequiredBonus()), FOURTH);
        lottoResultLottoRankMap.put(new DefaultLottoResult(FIFTH.getMatchCount(), FIFTH.isRequiredBonus()), FIFTH);
    }

    @Override
    public LottoRank grade(LottoResult lottoResult) {
        return lottoResultLottoRankMap.getOrDefault(lottoResult, SIXTH);
    }
}
