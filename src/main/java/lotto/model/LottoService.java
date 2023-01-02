package lotto.model;

import lotto.util.RandomGenerator;

import java.util.List;

public class LottoService {

    private static final int DEFAULT_LOTTO_COST = 1000;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private Lottos lottos;
    private WinningNumbers winningNumbers;

    private final long purchaseCost;
    private final PriceResult priceResult;

    public LottoService(long purchaseCost) {
        this.purchaseCost = purchaseCost;
        this.priceResult = new PriceResult();
    }

    public void createLottos() {
        this.lottos = new Lottos((int) purchaseCost / DEFAULT_LOTTO_COST);
    }

    public void createWinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new WinningNumbers(winningNumbers, bonusNumber);
    }

    public void createLottos(int lottoCost) {
        this.lottos = new Lottos((int) purchaseCost / lottoCost);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public PriceResult getResult() {

        for (Lotto lotto : lottos.getLottos()) {
            priceResult.saveResult(winningNumbers.getPrice(lotto));
        }
        return priceResult;
    }

    public double calculateEarningsRate() {
        return (double) priceResult.sumPrice() / purchaseCost;
    }
}
