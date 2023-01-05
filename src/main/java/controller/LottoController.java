package controller;

import dto.LottoWinnerDto;
import model.LottoGame;
import view.View;
import utils.Parser;

import java.util.Scanner;

public class LottoController {
    private final LottoGame lottoGame;
    View view;

    public LottoController() {
        lottoGame = new LottoGame();
        view = new View();
    }

    public void start() {
        lottoGame.setLottoTicket(inputPurchaseMoney());
        view.outputLottoTicket(lottoGame.getLottoTicket());
        lottoGame.setLottoWinner(inputLottoWinner());
        view.outputResultMessage(lottoGame.getResult());
    }

    private long inputPurchaseMoney() {
        view.printPriceMessage();
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        scanner.nextLine(); //버퍼의 개행 비우기
        return input;
    }

    private LottoWinnerDto inputLottoWinner() {
        view.printWinnerNumberMessage();
        Scanner scanner = new Scanner(System.in);
        String winNumbers = scanner.nextLine();

        view.printBonusNumberMessage();
        int bonusNumber = scanner.nextInt();
        scanner.nextLine(); //버퍼의 개행 비우기

        return new LottoWinnerDto(Parser.parsingWinNumbers(winNumbers), Parser.parsingBonusNumber(bonusNumber));
    }


}
