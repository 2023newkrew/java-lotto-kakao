package lotto.domain;

import java.util.List;

public class Statistics {
    private long FIRST;
    private long SECOND;
    private long THIRD;
    private long FOURTH;
    private long FIFTH;
    private long purchase;

    public Statistics(){
        FIRST = 0;
        SECOND = 0;
        THIRD = 0;
        FOURTH = 0;
        FIFTH = 0;
    }

    public void build(List<Lotto> lottos, Lotto winLotto, LottoNumber bonusNumber) {
        for (Lotto lotto : lottos) {
            add(LottoRank.getRank(lotto, winLotto, bonusNumber));
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

    public long getByRank(LottoRank rank) {
        if(rank == LottoRank.FIRST) return FIRST;
        if(rank == LottoRank.SECOND) return SECOND;
        if(rank == LottoRank.THIRD) return THIRD;
        if(rank == LottoRank.FOURTH) return FOURTH;
        if(rank == LottoRank.FIFTH) return FIFTH;
        return 0;
    }

    public long getPrizeAmount() {
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
