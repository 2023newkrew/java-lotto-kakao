package lotto.domain;

import lotto.constant.LottoGrade;
import lotto.dto.GameResultDto;

import java.util.List;
import java.util.Map;

import static lotto.constant.ExceptionMessages.*;

public class LottoStore {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoStore() {
        lottos = new Lottos();
    }

    public void buyRandomLottosByAmounts(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(INVALID_LOTTO_MANUAL_NUMBERS);
        }
        this.lottos = this.lottos.addAll(Lottos.generateRandomLottosByAmounts(amount));
    }

    public void buyLottosByNumbers(List<List<Integer>> numbersList) {
        if (numbersList == null || numbersList.size() == 0) {
            throw new IllegalArgumentException(INVALID_RANDOM_LOTTO_AMOUNT);
        }
        this.lottos = this.lottos.addAll(Lottos.generateLottosByNumbers(numbersList));
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
        return (float) totalPrice / (lottos.size() * Lotto.LOTTO_PRICE);
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
