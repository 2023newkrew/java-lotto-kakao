package lotto.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final Map<Ranking, Integer> rankingMap;

    public Statistics(Map<Ranking, Integer> rankingMap) {
        this.rankingMap = rankingMap;
    }

    public static Statistics fromLotto(List<LottoTicket> tickets, LottoWinningNumber winningNumber) {
        Map<Ranking, Integer> rankingMap = new HashMap<>();
        for (LottoTicket ticket : tickets) {
            Ranking ranking = winningNumber.calculateRanking(ticket);
            rankingMap.put(ranking, rankingMap.getOrDefault(ranking, 0) + 1);
        }
        return new Statistics(rankingMap);
    }

    public int getOrDefault(Ranking key, int defaultValue) {
        return rankingMap.getOrDefault(key, defaultValue);
    }

    public long outcome() {
        return rankingMap.entrySet()
                .stream()
                .reduce(
                        0L,
                        (acc, other) -> acc + other.getKey()
                                .getPrice() * other.getValue(),
                        Long::sum
                );
    }
}
