package lotto;

import lotto.controller.LottoGame;
import lotto.domain.Customer;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;
import lotto.utils.LottoRank;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        // amount 입력
        int amount = inputView.inputUserAmount();

        // 로또 구매
        List<String> manual = inputView.inputManualNumbers(amount);
        LottoGame lottoGame = new LottoGame(amount, manual);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        resultView.printPurchaseCount(lottoGame.getCustomer(), manual.size());
        resultView.printLottoTickets(lottoGame.getCustomer());

        // 지난주 당첨 번호 및 보너스볼 입력
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();

        // Winner 뽑아내기
        LottoWinnerTicket lottoWinnerTicket = new LottoWinnerTicket(changeToTicket(winNumber), bonus);
        Map<LottoRank, Integer> result = lottoWinnerTicket.getResult(lottoGame.getCustomer());

        // 당첨 통계 출력
        double rate = lottoWinnerTicket.calcRateOfReturn(amount, result);
        resultView.printWinningStatics(result, rate);
    }

    // 사용자 입력값을 WinnerTicket 으로 변환
    private static LottoTicket changeToTicket(String userInput) {
        Integer[] numbers = stringToArray(userInput);
        return new LottoTicket(Arrays.asList(numbers));
    }

    private static Integer[] stringToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}
