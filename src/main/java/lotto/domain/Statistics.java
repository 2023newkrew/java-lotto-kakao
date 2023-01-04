package lotto.domain;

import java.util.List;

public class Statistics {
    private int FIRST;
    private int SECOND;
    private int THIRD;
    private int FOURTH;
    private int FIFTH;
    private int purchase;

    public Statistics(){
        FIRST = 0;
        SECOND = 0;
        THIRD = 0;
        FOURTH = 0;
        FIFTH = 0;
    }

    public void build(List<Lotto> lottos, Lotto winLotto, LottoNumber bonusNumber) {
        for (Lotto lotto : lottos) {
            LottoResult result = new LottoResult(lotto, winLotto, bonusNumber);
            add(result.getRank());
        }
    }

    public void add(LottoRank rank) {
        if(rank == LottoRank.FIRST) FIRST++;
        if(rank == LottoRank.SECOND) SECOND++;
        if(rank == LottoRank.THIRD) THIRD++;
        if(rank == LottoRank.FOURTH) FOURTH++;
        if(rank == LottoRank.FIFTH) FIFTH++;
        purchase += 1000;
    }

    public int getByRank(LottoRank rank) {
        if(rank == LottoRank.FIRST) return FIRST;
        if(rank == LottoRank.SECOND) return SECOND;
        if(rank == LottoRank.THIRD) return THIRD;
        if(rank == LottoRank.FOURTH) return FOURTH;
        if(rank == LottoRank.FIFTH) return FIFTH;
        return 0;
    }

    public int getPrizeAmount() {
        return FIRST * LottoRank.FIRST.PRIZE
                + SECOND * LottoRank.SECOND.PRIZE
                + THIRD * LottoRank.THIRD.PRIZE
                + FOURTH * LottoRank.FOURTH.PRIZE
                + FIFTH * LottoRank.FIFTH.PRIZE;
    }

    public double getProfitRate() {
        return ((double) getPrizeAmount() / (double) purchase);
    }
}
