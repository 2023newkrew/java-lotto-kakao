package lotto.domain;

import java.util.*;

import lotto.constant.LottoGradeEnum;
import lotto.dto.GameResultDto;

import static lotto.constant.MessageConstant.INVALID_PRICE_AMOUNT;

public class LottoGame {
    private final List<Lotto> lottos;
    private final WinningLotto winningLotto;

    public LottoGame(List<Lotto> lottos, WinningLotto winningLotto) {
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
        Map<LottoGradeEnum, Integer> lottoResultCounter = new HashMap<>();
        initiateCounter(lottoResultCounter);

        lottos.forEach(lotto -> lottoResultCounter.put(
                winningLotto.getGrade(lotto),
                lottoResultCounter.get(winningLotto.getGrade(lotto)) + 1)
        );

        return new GameResultDto(lottoResultCounter, getEarningRate(lottoResultCounter));
    }

    private void initiateCounter(Map<LottoGradeEnum, Integer> counter) {
        Arrays.stream(LottoGradeEnum.values())
                .forEach(lottoGradeEnum -> counter.put(lottoGradeEnum, 0));
    }

    private float getEarningRate(Map<LottoGradeEnum, Integer> lottoResultCounter) {
        long totalPrice = lottoResultCounter.entrySet().stream()
                .mapToInt((entry) -> entry.getKey().price * entry.getValue())
                .sum();
        return (float) totalPrice / (lottos.size() * 1000);
    }
}
