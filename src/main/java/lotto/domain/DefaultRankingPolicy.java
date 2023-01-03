package lotto.domain;

import java.util.Map;

public class DefaultRankingPolicy implements RankingPolicy {

    Map<LottoResult, LottoRank> lottoResultLottoRankMap;

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
