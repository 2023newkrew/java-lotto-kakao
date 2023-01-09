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
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
        setRankCountBy(winningLotto, rankCount);
        return LottoResult.of(rankCount);
    }

    private void setRankCountBy(WinningLotto winningLotto, Map<Rank, Integer> rankCount) {
        lottos.stream()
                .map(winningLotto::getRank)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(rank -> {
                    int count = rankCount.get(rank);
                    rankCount.replace(rank, count + 1);
                });
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }

    public Lottos addAll(Lottos other) {
        List<Lotto> totalLottos = new ArrayList<>(this.lottos);
        totalLottos.addAll(other.lottos);
        return Lottos.from(totalLottos);
    }
}
