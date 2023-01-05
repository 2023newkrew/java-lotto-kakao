package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    public static Lottos createLottos(List<LottoNumbers> lottoNumbers, int autoCount, GeneratePolicy generatePolicy) {
        return new Lottos(lottoNumbers, autoCount, generatePolicy);
    }

    public Lottos(List<LottoNumbers> lottoNumbers, int autoCount, GeneratePolicy generatePolicy) {
        lottos = lottoNumbers.stream().map(Lotto::makeLotto).collect(Collectors.toList());
        IntStream.range(0, autoCount).forEach((i) -> lottos.add(Lotto.makeLotto(generatePolicy)));
    }

    public Map<LottoRank, Integer> getRanks(LottoAnswer lottoAnswer) {
        HashMap<LottoRank, Integer> ranks = new HashMap<>();
        addRank(ranks, lottos.stream().map(lotto -> lotto.match(lottoAnswer).getRank()).collect(Collectors.toList()));
        return ranks;
    }

    private void addRank(Map<LottoRank, Integer> rankMap, List<LottoRank> ranks) {
        ranks.forEach(rank -> {
            if (rankMap.containsKey(rank)) {
                rankMap.put(rank, rankMap.get(rank) + 1);
                return;
            }
            rankMap.put(rank, 1);
        });
    }
}
