package lotto.domain;

import lotto.constant.LottoGrade;
import lotto.dto.GameResultDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_POOL;

public class LottoStore {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoStore() {
        lottos = new Lottos();
    }

    private Lotto generateLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private Lotto generateRandomLotto() {
        Collections.shuffle(LOTTO_NUMBER_POOL);
        return new Lotto(LOTTO_NUMBER_POOL.subList(0, 6));
    }

    public void buyRandomLottosByAmounts(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, amount).forEach((i) -> lottos.add(generateRandomLotto()));
        this.lottos = this.lottos.addAll(lottos);
    }

    public void buyLottosByNumbers(List<List<Integer>> numbersList) {
        this.lottos = this.lottos.addAll(numbersList.stream()
                .map(this::generateLotto)
                .collect(Collectors.toList()));
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
