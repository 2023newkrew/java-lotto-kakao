package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.dto.GameResultDto;

public class LottoGame {

    private final List<Lotto> lottos;
    private final WinningLotto winningLotto;

    public LottoGame(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public GameResultDto getResult() {
        LottoGradeCounter lottoGradeCounter = getLottoGradeCounter();
        float rateOfReturn = (float) lottoGradeCounter.getTotalPrice() /
                (lottos.size() * LottoGenerator.LOTTO_PRICE);
        return new GameResultDto(lottoGradeCounter.getLottoGradeCountResults(), rateOfReturn);
    }

    private LottoGradeCounter getLottoGradeCounter() {
        LottoGradeCounter lottoGradeCounter = new LottoGradeCounter();
        lottos.forEach(lotto -> lottoGradeCounter.putGrade(winningLotto.getGrade(lotto)));
        return lottoGradeCounter;
    }

}
