package lotto.view;

import static lotto.view.OutputMessage.*;


import lotto.domain.LottoHandler;

public class OutputView {
    public void printReadPrice() {
        System.out.println(PRINT_READ_PRICE_MESSAGE.getMessage());
    }

    public void printCount(int passiveLottoCount, int autoLottoCount) {
        System.out.printf(PRINT_COUNT_MESSAGE.getMessage(), passiveLottoCount, autoLottoCount);
    }

    public void printAllLotto(LottoHandler lottoHandler) {
        System.out.println(lottoHandler.allLottoToString());

    }

    public void printInputRequestOfWinningNumber() {
        System.out.println(PRINT_READ_LOTTO_ANSWER_MESSAGE.getMessage());
    }

    public void printReadBonusBall() {
        System.out.println(PRINT_READ_BONUS_BALL_MESSAGE.getMessage());
    }


    public void printReadPassiveLottoCount() {
        System.out.println(OutputMessage.PRINT_READ_PASSIVE_LOTTO_COUNT_MESSAGE.getMessage());
    }


    public void printReadPassiveLottoNumber() {
        System.out.println(OutputMessage.PRINT_READ_PASSIVE_LOTTO_NUMBERS_MESSAGE.getMessage());
    }

    public void printResult(String lottoResultString) {
        System.out.println(PRINT_WINNING_RESULT_MESSAGE.getMessage()+lottoResultString);

    }
}
