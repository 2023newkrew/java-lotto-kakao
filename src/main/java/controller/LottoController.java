package controller;

import dto.LottoWinnerDto;
import model.Lotto;
import model.LottoGame;
import model.LottoNumber;
import view.View;
import utils.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoController {
    private final LottoGame lottoGame;
    View view;

    public LottoController() {
        lottoGame = new LottoGame();
        view = new View();
    }

    public void start() {
        lottoGame.setPurchaseMoney(inputPurchaseMoney());
        int manualLottoCount = inputManualLottoCount(lottoGame.getTotalLottoCount());
        lottoGame.setLottoTicket(inputManualLottos(manualLottoCount));
        view.printAutomaticLottoMessage(manualLottoCount, lottoGame.getTotalLottoCount() - manualLottoCount);
        view.outputLottoTicket(lottoGame.getLottoTicket());
        lottoGame.setLottoWinner(inputLottoWinner(inputWinNumbers(), inputBonusNumber()));
        view.outputResultMessage(lottoGame.getResult());
    }

    private int inputManualLottoCount(int totalLottoCount) {
        view.printManualLottoCountMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            return Parser.parsingManualLottoCount(input, totalLottoCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputManualLottoCount(totalLottoCount);
        }
    }

    private List<Lotto> inputManualLottos(int manualLottoCount) {
        view.printManualLottoMessage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(inputManualLotto());
        }
        return manualLottos;
    }

    private Lotto inputManualLotto() {
        Scanner scanner = new Scanner(System.in);
        String lottoNumbers = scanner.nextLine();

        try {
            return Parser.parsingStringToLotto(lottoNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputManualLotto();
        }
    }

    private long inputPurchaseMoney() {
        view.printPriceMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            return Parser.parsingPurchaseMoney(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private Lotto inputWinNumbers() {
        view.printWinnerNumberMessage();
        Scanner scanner = new Scanner(System.in);
        String winNumbers = scanner.nextLine();

        try {
            return Parser.parsingStringToLotto(winNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputWinNumbers();
        }
    }

    private LottoNumber inputBonusNumber() {
        view.printBonusNumberMessage();
        Scanner scanner = new Scanner(System.in);
        String bonusNumber = scanner.nextLine();

        try {
            return Parser.parsingStringToLottoNumber(bonusNumber);
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
