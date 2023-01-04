package lotto.controller;

import static lotto.domain.LottoConstants.*;

import lotto.domain.LottoAnswer;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGame lottoGame;

    public LottoController(InputView inputView, OutputView outputView, LottoGame lottoGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGame = lottoGame;
    }


    public void run() {
        int lottoCount = readAmountAndCalculateLottoCount();
        lottoGame.createLotto(lottoCount);
        outputView.printAllLotto(lottoGame.getLottoHandler());
        readLottoAnswer();
        lottoGame.grade();
        outputView.printGameResult(lottoGame.getGameResultDto());
    }

    private void readLottoAnswer() {
        outputView.printInputRequestOfWinningNumber();
        LottoNumbers lottoAnswerNumbers = new LottoNumbers(inputView.readLottoAnswerNumbers());
        outputView.printReadBonusBall();
        lottoGame.setLottoAnswer(new LottoAnswer(lottoAnswerNumbers, inputView.readBonusBall()));
    }

    private int readAmountAndCalculateLottoCount() {
        outputView.printReadPrice();
        int lottoCount = calculateLottoCount();
        outputView.printCount(lottoCount);
        return lottoCount;
    }

    private int calculateLottoCount() {
        return inputView.readPrice() / LOTTO_PRICE;
    }


}
