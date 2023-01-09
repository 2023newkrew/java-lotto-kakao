package lotto.domain;

import static lotto.LottoConfig.LOTTO_PRICE;

import lotto.dto.GameResultDto;

public class LottoGame {

    private final Customer customer;
    private final WinningLotto winningLotto;

    public LottoGame(Customer customer, WinningLotto winningLotto) {
        this.customer = customer;
        this.winningLotto = winningLotto;
    }

    public GameResultDto getGameResult() {
        LottoGradeCounter lottoGradeCounter = getLottoGradeCounter();
        float rateOfReturn = (float) lottoGradeCounter.getTotalPrice() /
                (customer.getEveryLottoSize() * LOTTO_PRICE);
        return new GameResultDto(lottoGradeCounter.getLottoGradeCountResults(), rateOfReturn);
    }

    private LottoGradeCounter getLottoGradeCounter() {
        LottoGradeCounter lottoGradeCounter = new LottoGradeCounter();
        customer.getEveryLottos()
                .forEach(lotto -> lottoGradeCounter.putGrade(winningLotto.getGrade(lotto)));
        return lottoGradeCounter;
    }

}
