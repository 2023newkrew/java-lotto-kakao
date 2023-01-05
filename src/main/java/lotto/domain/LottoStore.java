package lotto.domain;

import lotto.constant.LottoGrade;
import lotto.dto.GameResultDto;

import java.util.List;
import java.util.Map;

import static lotto.constant.ExceptionMessages.*;

public class LottoStore {
    public static final int LOTTO_PRICE = 1000;

    private Lottos lottos;
    private WinningLotto winningLotto;
    private int money;

    public LottoStore(int money) {
        lottos = new Lottos();
        this.money = money;
    }

    public void buyRandomLottosByMoneyLeft() {
        this.lottos = this.lottos.addAll(Lottos.generateRandomLottosByAmounts(money / LOTTO_PRICE));
        money -= (money / LOTTO_PRICE) * LOTTO_PRICE;
    }

    public void buyLottosByNumbers(List<List<Integer>> numbersList) {
        if (numbersList == null || numbersList.size() == 0)
            throw new IllegalArgumentException(INVALID_RANDOM_LOTTO_AMOUNT);
        if (numbersList.size() * LOTTO_PRICE > money)
            throw new IllegalArgumentException(INVALID_MONEY_LEFT);
        this.lottos = this.lottos.addAll(Lottos.generateLottosByNumbers(numbersList));
        money -= numbersList.size() * LOTTO_PRICE;
    }

    public GameResultDto getLottosResult() {
        if (winningLotto == null)
            throw new IllegalArgumentException(INVALID_WINNING_LOTTO);
        Map<LottoGrade, Integer> lottosResult = lottos.getResult(winningLotto);
        return new GameResultDto(lottosResult, getEarningRate(lottosResult));
    }

    private float getEarningRate(Map<LottoGrade, Integer> lottoResultCounter) {
        long totalPrice = lottoResultCounter.entrySet().stream()
                .mapToLong((entry) -> (long) entry.getKey().price * entry.getValue())
                .sum();
        return (float) totalPrice / (lottos.size() * LOTTO_PRICE);
    }

    public int getLottoAmount() {
        return lottos.size();
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
