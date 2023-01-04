package lotto.controller;

import static lotto.domain.LottoConstants.*;

import lotto.domain.DefaultRankingPolicy;
import lotto.domain.LottoAnswer;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGame lottoGame = new LottoGame(new DefaultRankingPolicy());
    private int lottoCount;
    private LottoNumbers lottoAnswerNumbers;

    public void run() {
        initialize();
        injectLottoAnswerNumber();
        injectLottoAnswer();
        lottoGame.grade();
        printResult();
    }

    private void initialize() {
        try {
            setPrice();
            lottoGame.createLotto(lottoCount);
            outputView.printAllLotto(lottoGame.getLottoHandler());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initialize();
        }
    }

    private void setPrice() {
        outputView.printReadPrice();
        lottoCount = inputView.readPrice() / (int) LOTTO_UNIT_PRICE;
        outputView.printCount(lottoCount);
    }

    private void injectLottoAnswerNumber() {
        try {
            outputView.printReadLottoAnswerNumbers();
            lottoAnswerNumbers = new LottoNumbers(inputView.readLottoAnswerNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            injectLottoAnswerNumber();
        }
    }

    private void injectLottoAnswer() {
        try {
            outputView.printReadBonusBall();
            lottoGame.setLottoAnswer(new LottoAnswer(lottoAnswerNumbers, inputView.readBonusBall()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            injectLottoAnswer();
        }
    }

    private void printResult() {
        outputView.printGameResult(lottoGame.getGameResult());
    }
}
