package controller;

import dto.*;
import model.*;
import utils.*;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoController {
    private final LottoGame lottoGame;
    private final View view;

    private Scanner scanner = new Scanner(System.in);

    public LottoController() {
        lottoGame = new LottoGame();
        view = new View();
    }

    public void start() {
        lottoGame.setPurchaseMoney(inputPurchaseMoney());
        int manualLottoCount = setManualLottoCount();
        lottoGame.setLottoTicket(inputManualLottos(manualLottoCount));
        view.printLottoCountMessage(manualLottoCount, lottoGame.getTotalLottoCount() - manualLottoCount);
        view.outputLottoTicket(lottoGame.getLottoTicket());
        view.printWinnerNumberMessage();
        lottoGame.setLottoWinner(inputLottoWinner(inputLotto()));
        view.outputResultMessage(lottoGame.getResult());
    }

    private PurchaseMoney inputPurchaseMoney() {
        view.printPriceMessage();
        Context<PurchaseMoney> context = new Context<>(
                param -> Parser.parsingStringToPurchaseMoney(param),
                () -> inputPurchaseMoney()
        );
        return context.execute(scanner.nextLine());
    }

    private int setManualLottoCount() {
        view.printManualLottoCountMessage();
        Context<Integer> context = new Context<>(
                param -> lottoGame.setManualLottoCount(Parser.parsingStringToInt(param)),
                () -> setManualLottoCount()
        );
        return context.execute(scanner.nextLine());
    }

    private ManualLottoDto inputManualLottos(int manualLottoCount) {
        view.printManualLottoMessage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(inputLotto());
        }
        return new ManualLottoDto(manualLottos);
    }

    private Lotto inputLotto() {
        String input = scanner.nextLine();
        Context<Lotto> context = new Context<>(
                param -> Parser.parsingStringToLotto(param),
                () -> inputLotto()
        );
        return context.execute(input);
    }

    private LottoNumber inputBonusNumber() {
        view.printBonusNumberMessage();
        Context<LottoNumber> context = new Context<>(
                param -> Parser.parsingStringToLottoNumber(param),
                () -> inputBonusNumber()
        );
        return context.execute(scanner.nextLine());
    }

    private LottoWinnerDto inputLottoWinner(Lotto winNumbers) {
        Context<LottoWinnerDto> context = new Context<>(
                param -> new LottoWinnerDto(winNumbers, inputBonusNumber()),
                () -> inputLottoWinner(winNumbers)
        );
        return context.execute("");
    }
}
