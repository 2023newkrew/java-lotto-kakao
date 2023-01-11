package lotto.model;

import java.util.List;

public class LottoService {

    public static final int LOTTO_COST = 1000;

    private Lottos lottos;
    private WinningLotto winningLotto;

    private final long purchaseCost;
    private final PriceResult priceResult;

    public LottoService(long purchaseCost) {
        this.purchaseCost = purchaseCost;
        this.priceResult = new PriceResult();
    }

    public void createLottos(List<List<Integer>> manualLottoNumbers, int lottoCount) {
        this.lottos = new Lottos(manualLottoNumbers, lottoCount);
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
