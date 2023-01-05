package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.config.AppConfig;

public class Lottos {

    private final List<Lotto> lottos;

    public static Lottos createLottos(List<LottoNumbers> lottoNumbers, int autoCount) {
        return new Lottos(lottoNumbers, autoCount);
    }

    public Lottos(List<LottoNumbers> lottoNumbers, int autoCount) {
        lottos = lottoNumbers.stream().map(Lotto::makeLotto).collect(Collectors.toList());
        IntStream.range(0, autoCount).forEach((i) -> lottos.add(Lotto.makeLotto(AppConfig.getGeneratePolicy())));
    }

    public LottoRanks getRanks(LottoAnswer lottoAnswer) {
        HashMap<LottoRank, Integer> ranks = new HashMap<>();
        initRanks(ranks);
        addRank(ranks, lottos.stream().map(lotto -> lotto.match(lottoAnswer).getRank()).collect(Collectors.toList()));
        return LottoRanks.createLottoRanks(ranks);
    }

    private void initRanks(HashMap<LottoRank, Integer> ranks) {
        Arrays.stream(LottoRank.values()).forEach(rank -> ranks.put(rank, 0));
    }

    private void addRank(Map<LottoRank, Integer> rankMap, List<LottoRank> ranks) {
        ranks.forEach(rank -> rankMap.put(rank, rankMap.get(rank) + 1));
    }
}
