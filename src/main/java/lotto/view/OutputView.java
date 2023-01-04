package lotto.view;

import static lotto.view.OutputMessage.*;

import lotto.dto.GameResultDto;
import lotto.domain.LottoHandler;

public class OutputView {
    public void printReadPrice() {
        System.out.println(PRINT_READ_PRICE_MESSAGE.getMessage());
    }

    public void printCount(int lottoCount) {
        System.out.printf(PRINT_COUNT_MESSAGE.getMessage(), lottoCount);
    }

    public void printAllLotto(LottoHandler lottoHandler) {
        System.out.println(lottoHandler.toString());
    }

    public void printInputRequestOfWinningNumber() {
        System.out.println(PRINT_READ_LOTTO_ANSWER_MESSAGE.getMessage());
    }

    public void printReadBonusBall() {
        System.out.println(PRINT_READ_BONUS_BALL_MESSAGE.getMessage());
    }

    public void printGameResult(GameResultDto gameResultDto) {
        System.out.println(PRINT_WINNING_RESULT_MESSAGE.getMessage());
        System.out.println(gameResultDto.getResult());
        System.out.printf(PRINT_STATICS_MESSAGE.getMessage(), gameResultDto.getYield());
    }
}
