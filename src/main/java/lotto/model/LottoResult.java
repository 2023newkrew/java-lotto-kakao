package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCountMap;

    public LottoResult() {
        rankCountMap = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            rankCountMap.put(rank, 0);
        }
    }

    public LottoResult(List<Integer> rankList) {
        rankCountMap = Map.of(
                LottoRank.RANK1, rankList.get(0),
                LottoRank.RANK2, rankList.get(1),
                LottoRank.RANK3, rankList.get(2),
                LottoRank.RANK4, rankList.get(3),
                LottoRank.RANK5, rankList.get(4),
                LottoRank.RANK6, rankList.get(5)
        );
    }

    public void addLottoRank(LottoRank lottoRank) {
        rankCountMap.put(lottoRank, rankCountMap.get(lottoRank)+1);
    }

    public Map<LottoRank, Integer> getRankCountMap(){
        return rankCountMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;

        return Objects.equals(rankCountMap, that.rankCountMap);
    }

}
