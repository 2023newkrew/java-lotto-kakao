package lotto.rankingpolicy;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

public class DefaultRankingPolicy implements RankingPolicy {

    private Map<LottoResult, LottoRank> lottoResultLottoRankMap = new HashMap<>();

    public DefaultRankingPolicy() {
        lottoResultLottoRankMap.put(new LottoResult(6, false), LottoRank.FIRST);
        lottoResultLottoRankMap.put(new LottoResult(5, true), LottoRank.SECOND);
        lottoResultLottoRankMap.put(new LottoResult(5, false), LottoRank.THIRD);
        lottoResultLottoRankMap.put(new LottoResult(4, true), LottoRank.FOURTH);
        lottoResultLottoRankMap.put(new LottoResult(4, false), LottoRank.FOURTH);
        lottoResultLottoRankMap.put(new LottoResult(3, true), LottoRank.FIFTH);
        lottoResultLottoRankMap.put(new LottoResult(3, false), LottoRank.FIFTH);
    }

    @Override
    public LottoRank grade(LottoResult lottoResult) {
        return lottoResultLottoRankMap.getOrDefault(lottoResult, LottoRank.SIXTH);
    }
}
