package lotto.domain;

import java.util.*;

import lotto.constant.LottoGrade;
import lotto.dto.GameResultDto;

import static lotto.constant.ExceptionMessages.INVALID_PRICE_AMOUNT;

public class Lottos {
    private final List<Lotto> lottos;
    private final WinningLotto winningLotto;

    public Lottos(List<Lotto> lottos, WinningLotto winningLotto) {
        validateLottos(lottos);
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    private void validateLottos(List<Lotto> lottos) {
        if (lottos == null || lottos.size() == 0)
            throw new IllegalArgumentException(INVALID_PRICE_AMOUNT);
    }

    public GameResultDto getResult() {
        Map<LottoGrade, Integer> lottoResultCounter = new HashMap<>();
        initiateCounter(lottoResultCounter);

        lottos.forEach(lotto -> lottoResultCounter.put(
                winningLotto.getGrade(lotto),
                lottoResultCounter.get(winningLotto.getGrade(lotto)) + 1)
        );

        return new GameResultDto(lottoResultCounter, getEarningRate(lottoResultCounter));
    }

    private void initiateCounter(Map<LottoGrade, Integer> counter) {
        for (LottoGrade lottoGrade: LottoGrade.values()) {
            counter.put(lottoGrade, 0);
        }
    }

    private float getEarningRate(Map<LottoGrade, Integer> lottoResultCounter) {
        long totalPrice = lottoResultCounter.entrySet().stream()
                .mapToLong((entry) -> (long) entry.getKey().price * entry.getValue())
                .sum();
        return (float) totalPrice / (lottos.size() * Lotto.LOTTO_PRICE);
    }
}
