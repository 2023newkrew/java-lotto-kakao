package lotto.controller;

import static lotto.domain.LottoConstants.*;

import lotto.domain.LottoAnswer;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    InputView inputView;
    OutputView outputView;

    LottoGame lottoGame;

    public LottoController(InputView inputView, OutputView outputView, LottoGame lottoGame){
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGame = lottoGame;
    }


    public void run() {
        outputView.printReadPrice();
        int lottoCount = inputView.readPrice()/(int) LOTTO_PRICE;
        outputView.printCount(lottoCount);
        lottoGame.createLotto(lottoCount);
        outputView.printAllLotto(lottoGame.getLottoHandler());
        outputView.printReadLottoAnswerNumbers();
        LottoNumbers lottoAnswerNumbers = new LottoNumbers(inputView.readLottoAnswerNumbers());
        outputView.printReadBonusBall();
        lottoGame.setLottoAnswer(new LottoAnswer(lottoAnswerNumbers, inputView.readBonusBall()));
        lottoGame.grade();
        outputView.printGameResult(lottoGame.getGameResultDto());
    }




}
