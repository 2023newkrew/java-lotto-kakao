package lotto.view;

import static lotto.view.OutputMessage.*;

import lotto.dto.GameResultDto;
import lotto.domain.LottoHandler;

public class OutputView {
    public void printReadPrice() {
        System.out.println(PRINT_READ_PRICE.getMessage());
    }

    public void printCount(int lottoCount) {
        System.out.printf(PRINT_COUNT.getMessage(), lottoCount);
    }

    public void printAllLotto(LottoHandler lottoHandler) {
        System.out.println(lottoHandler.toString());
    }

    public void printReadLottoAnswerNumbers() {
        System.out.println(PRINT_READ_LOTTO_ANSWER.getMessage());
    }

    public void printReadBonusBall() {
        System.out.println(PRINT_READ_BONUS_BALL.getMessage());
    }

    public void printGameResult(GameResultDto gameResultDto) {
        System.out.println(PRINT_WINNING_RESULT.getMessage());
        System.out.println(gameResultDto.getResult());
        System.out.printf(PRINT_STATICS.getMessage(), gameResultDto.getYield());
    }
}
