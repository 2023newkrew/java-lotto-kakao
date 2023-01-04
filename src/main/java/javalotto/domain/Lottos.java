package javalotto.domain;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos fromNumbers(List<List<Integer>> lottoNumbers) {
        List<Lotto> lottos = lottoNumbers.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public static Lottos of(Lottos...manyLottos) {
        List<Lotto> allLottos = Arrays.stream(manyLottos)
                .map(lottos -> lottos.lottos)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return new Lottos(allLottos);
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

        stream(Rank.values())
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
