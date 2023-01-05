package lotto.domain;

import lotto.constant.LottoGrade;
import lotto.dto.GameResultDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStore {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoStore() {
        lottos = new Lottos();
    }

    public void buyRandomLottosByAmounts(int amount) {
        this.lottos = this.lottos.addAll(Lottos.generateRandomLottosByAmounts(amount));
    }

    public void buyLottosByNumbers(List<List<Integer>> numbersList) {
        this.lottos = this.lottos.addAll(Lottos.generateLottosByNumbers(numbersList));
    }

    public GameResultDto getLottosResult() {
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
