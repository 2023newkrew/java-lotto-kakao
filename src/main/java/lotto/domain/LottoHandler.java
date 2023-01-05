package lotto.domain;

import java.util.List;
import java.util.Map;
import lotto.config.AppConfig;

public class LottoHandler {
    private Lottos lottos;
    private LottoAnswer lottoAnswer;

    private LottoHandler() {}

    public static LottoHandler createLottoHandler(){
        return new LottoHandler();
    }

    public void createLottos(List<LottoNumbers> lottoNumbers, int autoCount) {
        lottos = Lottos.createLottos(lottoNumbers, autoCount);
    }

    public void setLottoAnswer(List<Integer> lottoNumber, int bonusBall) {
        lottoAnswer = LottoAnswer.createLottoAnswer(lottoNumber, bonusBall);
    }


    public String getLottoResultString() {
        LottoRanks ranks = lottos.getRanks(lottoAnswer);
        return ranks.getResultString();
    }


}
