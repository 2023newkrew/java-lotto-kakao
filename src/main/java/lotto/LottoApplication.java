package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int amount = inputView.inputUserAmount();

        // 로또 구매
        LottoController lottoController = new LottoController(amount);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        resultView.printLottoTickets(lottoController.getLottoTickets());

        // 지난주 당첨 번호 입력
        inputView.inputWinNumber();
        // 보너스볼 입력
        inputView.inputBonusNumber();

        // Winner 뽑아내기
        ArrayList<Integer> result = lottoController.getResult();

        // 당첨 통계 출력
        resultView.printWinningStatics(result);
    }
}
