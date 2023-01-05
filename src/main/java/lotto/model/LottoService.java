package lotto.model;

import java.util.List;

public class LottoService {

    private static final int DEFAULT_LOTTO_COST = 1000;

    private Lottos lottos;
    private WinningLotto winningLotto;

    private final long purchaseCost;
    private final PriceResult priceResult;

    public LottoService(long purchaseCost) {
        this.purchaseCost = purchaseCost;
        this.priceResult = new PriceResult();
    }

    public void createLottos() {
        this.lottos = new Lottos((int) purchaseCost / DEFAULT_LOTTO_COST);
    }

    public void createLottos(int lottoCost) {
        this.lottos = new Lottos((int) purchaseCost / lottoCost);
    }

    public void createWinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public PriceResult getResult() {

        for (Lotto lotto : lottos.getLottos()) {
            priceResult.saveResult(winningLotto.getPrice(lotto));
        }
        return priceResult;
    }

    public double calculateEarningsRate() {
        return (double) priceResult.sumPrice() / purchaseCost;
    }
}
