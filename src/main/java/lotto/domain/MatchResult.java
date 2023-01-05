package lotto.domain;

import java.util.Map;

public class MatchResult {
    private final Map<Ranking, Long> rankingCount;

    public MatchResult(Map<Ranking, Long> rankingCount) {
        this.rankingCount = rankingCount;
    }

    public long getCount(Ranking ranking) {
        return rankingCount.getOrDefault(ranking, 0L);
    }

    public double calculateProfit() {
        long ticketNumber = 0;
        int prize = 0;
        for (Ranking ranking : Ranking.values()) {
            ticketNumber += getCount(ranking);
            prize += ranking.getPrize() * getCount(ranking);
        }
        return ((double) prize) / (ticketNumber * 1000);
    }
}
