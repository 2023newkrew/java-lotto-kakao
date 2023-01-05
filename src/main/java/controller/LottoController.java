package controller;

import dto.LottoWinnerDto;
import model.Lotto;
import model.LottoGame;
import model.LottoNumber;
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
        lottoGame.setLottoWinner(inputLottoWinner(inputWinNumbers(), inputBonusNumber()));
        view.outputResultMessage(lottoGame.getResult());
    }

    private long inputPurchaseMoney() {
        view.printPriceMessage();
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        scanner.nextLine(); //버퍼의 개행 비우기
        return input;
    }

    private Lotto inputWinNumbers() {
        view.printWinnerNumberMessage();
        Scanner scanner = new Scanner(System.in);
        String winNumbers = scanner.nextLine();

        try {
            return Parser.parsingWinNumbers(winNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputWinNumbers();
        }
    }

    private LottoNumber inputBonusNumber() { //로또 번호랑 보너스 번호가 같을 때.. 에러를 발생시켜야함
        view.printBonusNumberMessage();
        Scanner scanner = new Scanner(System.in);
        String bonusNumber = scanner.nextLine();

        try {
            return Parser.parsingBonusNumber(bonusNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputBonusNumber();
        }
    }

    private LottoWinnerDto inputLottoWinner(Lotto winNumbers, LottoNumber bonusNumber) {
        try {
            return new LottoWinnerDto(winNumbers, bonusNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bonusNumber = inputBonusNumber();
            return inputLottoWinner(winNumbers, bonusNumber);
        }
    }
}
