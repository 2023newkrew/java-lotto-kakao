package javalotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;

public class LottoBuyer {
    private final PurchaseAmount purchaseAmount;
    private final TotalLottoCount totalLottoCount;
    private Lottos lottos;

    private LottoBuyer(PurchaseAmount purchaseAmount, TotalLottoCount totalLottoCount, Lottos lottos) {
        this.purchaseAmount = purchaseAmount;
        this.totalLottoCount = totalLottoCount;
        this.lottos = lottos;
    }

    public static LottoBuyer of(PurchaseAmount purchaseAmount, TotalLottoCount totalLottoCount) {
        return new LottoBuyer(purchaseAmount, totalLottoCount, Lottos.none());
    }

    public Lottos purchaseLottos(LottoShop lottoShop, List<List<Integer>> manualLottoNumbers) {
        Lottos manualLottos = lottoShop.issueManualLottos(manualLottoNumbers);
        Lottos autoLottos = lottoShop.issueAutoLottos(totalLottoCount.getAutoLottoCount());

        lottos = Lottos.of(manualLottos, autoLottos);

        return lottos;
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
        lottos.getLottos().stream()
                .map(winningLotto::getRank)
                .flatMap(Optional::stream)
                .forEach(rank -> rankCountMap.replace(rank, rankCountMap.get(rank) + 1));
    }

    public double getRateOfReturn(LottoResult lottoResult) {
        return (double) lottoResult.getTotalPrizeAmount() / (double) purchaseAmount.getPurchaseAmount();
    }

    public int getAutoLottoCount() {
        return totalLottoCount.getAutoLottoCount();
    }

    public int getManualLottoCount() {
        return totalLottoCount.getManualLottoCount();
    }
}
