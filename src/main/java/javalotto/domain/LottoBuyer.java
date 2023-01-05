package javalotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

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
        Map<Rank, Long> rankCountMap = lottos.getLottos().stream()
                .map(winningLotto::getRank)
                .flatMap(Optional::stream)
                .collect(groupingBy(Function.identity(), counting()));

        stream(Rank.values())
                .forEach(rank -> rankCountMap.putIfAbsent(rank, 0L));

        return LottoResult.from(rankCountMap);
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
