package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

import lotto.constant.LottoGrade;

import static lotto.constant.ExceptionMessages.INVALID_PRICE_AMOUNT;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        validateLottos(lottos);
        this.lottos = lottos;
    }

    public static Lottos generateRandomLottosByAmounts(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(Lotto.generateRandomLotto());
        }
        return new Lottos(lottos);
    }

    public static Lottos generateLottosByNumbers(List<List<Integer>> numbersList) {
        return new Lottos(numbersList.stream()
                .map(Lotto::generateLotto)
                .collect(Collectors.toList()));
    }

    private void validateLottos(List<Lotto> lottos) {
        if (lottos == null || lottos.size() == 0)
            throw new IllegalArgumentException(INVALID_PRICE_AMOUNT);
    }

    public Lottos addAll(List<Lotto> lottos) {
        List<Lotto> created = new ArrayList<>(this.lottos);
        created.addAll(lottos);

        return new Lottos(created);
    }

    public Lottos addAll(Lottos lottos) {
        return addAll(lottos.getLottos());
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Map<LottoGrade, Integer> getResult(WinningLotto winningLotto) {
        Map<LottoGrade, Integer> lottoResultCounter = new HashMap<>();
        initiateCounter(lottoResultCounter);

        lottos.forEach(lotto -> lottoResultCounter.put(
                winningLotto.getGrade(lotto),
                lottoResultCounter.get(winningLotto.getGrade(lotto)) + 1)
        );
        return lottoResultCounter;
    }

    private void initiateCounter(Map<LottoGrade, Integer> counter) {
        for (LottoGrade lottoGrade: LottoGrade.values()) {
            counter.put(lottoGrade, 0);
        }
    }
}
