package controller;

import dto.LottoWinnerDto;
import exception.LottoNumberException;
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
        } catch (LottoNumberException ex) {
            System.out.println(ex.getMessage());
            return inputWinNumbers();
        }
    }

    private LottoNumber inputBonusNumber() {
        view.printBonusNumberMessage();
        Scanner scanner = new Scanner(System.in);
        int bonusNumber = scanner.nextInt();
        scanner.nextLine(); //버퍼의 개행 비우기

        try {
            return Parser.parsingBonusNumber(bonusNumber);
        } catch (LottoNumberException ex) {
            System.out.println(ex.getMessage());
            return inputBonusNumber();
        }
    }
    private LottoWinnerDto inputLottoWinner(Lotto winNumbers, LottoNumber bonusNumber) {
        return new LottoWinnerDto(winNumbers, bonusNumber);
    }


}
