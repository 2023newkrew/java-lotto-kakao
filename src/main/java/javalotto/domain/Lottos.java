package javalotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public LottoResult getLottoResult(WinningLotto winningLotto) {
        Map<Rank, Integer> rankCountMap = initRankCountMap();
        setRankCountMapValues(rankCountMap, winningLotto);

        return LottoResult.of(rankCountMap);
    }

    private Map<Rank, Integer> initRankCountMap() {
        Map<Rank, Integer> rankCountMap = new HashMap<>();

        Arrays.stream(Rank.values())
                .forEach(rank -> rankCountMap.put(rank, 0));

        return rankCountMap;
    }

    private void setRankCountMapValues(Map<Rank, Integer> rankCountMap, WinningLotto winningLotto) {
        lottos.stream()
                .map(winningLotto::getRank)
                .flatMap(Optional::stream)
                .forEach(rank -> rankCountMap.replace(rank, rankCountMap.get(rank) + 1));
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }
}
