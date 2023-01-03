package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import lotto.LottoGradeEnum;
import lotto.dto.GameResultDto;
import lotto.dto.LottoResult;

public class LottoGame {

    private final List<Lotto> lottos;
    private final WinningLotto winningLotto;

    public LottoGame(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public GameResultDto getResult() {
        List<LottoResult> lottoResults = new ArrayList<>();
        HashMap<LottoGradeEnum, Integer> lottoResultCounter = getLottoResultCounter();
        int totalPrice = 0;
        for (Entry<LottoGradeEnum, Integer> entry : lottoResultCounter.entrySet()) {
            lottoResults.add(new LottoResult(entry.getKey(), entry.getValue()));
            totalPrice += entry.getKey().price * entry.getValue();
        }
        Collections.sort(lottoResults);
        float rate = (float) totalPrice / (lottos.size() * 1000);
        return new GameResultDto(lottoResults, rate);
    }

    private HashMap<LottoGradeEnum, Integer> getLottoResultCounter() {
        HashMap<LottoGradeEnum, Integer> lottoResultCounter = new HashMap<>();
        Arrays.stream(LottoGradeEnum.values())
                .forEach(lottoGradeEnum -> lottoResultCounter.put(lottoGradeEnum, 0));
        lottoResultCounter.remove(LottoGradeEnum.NONE_GRADE);
        lottos.forEach(lotto -> putGrade(lottoResultCounter, winningLotto.getGrade(lotto)));
        return lottoResultCounter;
    }

    private static void putGrade(HashMap<LottoGradeEnum, Integer> lottoResultCounter,
            LottoGradeEnum grade) {
        if (lottoResultCounter.containsKey(grade)) {
            lottoResultCounter.put(grade, lottoResultCounter.get(grade) + 1);
        }
    }

}
