package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.factory.LottoNumbersFactory;

public class LottoHandler {
    private final RankingPolicy rankingPolicy;
    private final List<Lotto> lottos;

    public LottoHandler(int lottoCount, RankingPolicy rankingPolicy) {
        this.rankingPolicy = rankingPolicy;
        lottos = createLottos(lottoCount);
    }

    private List<Lotto> createLottos(int lottoCount) {
        return IntStream.range(0, lottoCount).boxed()
                .map((i) -> new Lotto(LottoNumbersFactory.create())).collect(
                        Collectors.toList());
    }

    public List<Integer> grade(LottoAnswer lottoAnswer) {
        List<Integer> rankCounts = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        for (Lotto lotto : lottos) {
            int index = rankingPolicy.grade(lotto.createLottoResult(lottoAnswer)).getIndex();
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
