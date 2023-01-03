package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.factory.LottoNumbersFactory;
import lotto.generatepolicy.DefaultGeneratePolicy;
import lotto.generatepolicy.GeneratePolicy;
import lotto.rankingpolicy.RankingPolicy;

public class LottoHandler {

    private RankingPolicy rankingPolicy;
    private int lottoCount;

    private final List<Lotto> lottos;

    public LottoHandler(int lottoCount, RankingPolicy rankingPolicy) {
        this(lottoCount, rankingPolicy, new DefaultGeneratePolicy());
    }

    public LottoHandler(int lottoCount, RankingPolicy rankingPolicy, GeneratePolicy generatePolicy) {
        this.lottoCount = lottoCount;
        this.rankingPolicy = rankingPolicy;
        lottos = createLottos(generatePolicy, lottoCount);
    }

    private List<Lotto> createLottos(GeneratePolicy generatePolicy, int lottoCount) {
        return IntStream.range(0, lottoCount).boxed()
                .map((i) -> new Lotto(LottoNumbersFactory.create(generatePolicy))).collect(
                        Collectors.toList());
    }

    public List<Integer> grade(LottoAnswer lottoAnswer) {
        List<Integer> rankCounts = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0));
        for (Lotto lotto : lottos) {
            int index = rankingPolicy.grade(lotto.createLottoResult(lottoAnswer)).index();
            rankCounts.set(index, rankCounts.get(index) + 1);
        }
        return rankCounts;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        lottos.forEach(lotto -> stringBuilder.append(lotto.toString()).append('\n'));
        return stringBuilder.toString();
    }
}
