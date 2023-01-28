package lotto.model;

import java.util.List;

public class LottoService {

    private static final int LOTTO_COST = 1000;

    private final Lottos lottos;
    private WinningLotto winningLotto;

    private final long purchaseCost;
    private final PriceResult priceResult;

    public LottoService(long purchaseCost, List<List<Integer>> manualLottoNumbers) {
        this.purchaseCost = purchaseCost;
        this.lottos = new Lottos(manualLottoNumbers,
                (int) (purchaseCost / LOTTO_COST) - manualLottoNumbers.size());
        this.priceResult = new PriceResult();
    }

    public void createWinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public PriceResult getResult() {
        lottos.saveResult(winningLotto, priceResult);
        return priceResult;
    }

    public double getEarningsRate() {
        return priceResult.calculateEarningsRate(purchaseCost);
    }
}
