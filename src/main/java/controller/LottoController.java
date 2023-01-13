package controller;

import dto.*;
import model.*;
import utils.*;
import utils.templatemethod.AbstractTemplate;
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
        AbstractTemplate<PurchaseMoney> template = new AbstractTemplate<>() {
            @Override
            protected PurchaseMoney parse(String param) {
                return Parser.parsingStringToPurchaseMoney(param);
            }

            @Override
            protected PurchaseMoney retry() {
                return inputPurchaseMoney();
            }
        };
        return template.run(scanner.nextLine());
    }

    private int setManualLottoCount() {
        view.printManualLottoCountMessage();
        AbstractTemplate<Integer> template = new AbstractTemplate<>() {
            @Override
            protected Integer parse(String param) {
                return lottoGame.setManualLottoCount(Parser.parsingStringToInt(param));
            }

            @Override
            protected Integer retry() {
                return setManualLottoCount();
            }
        };
        return template.run(scanner.nextLine());
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
        AbstractTemplate<Lotto> template = new AbstractTemplate<>() {
            @Override
            protected Lotto parse(String param) {
                return Parser.parsingStringToLotto(param);
            }

            @Override
            protected Lotto retry() {
                return inputLotto();
            }
        };
        return template.run(scanner.nextLine());
    }

    private LottoNumber inputBonusNumber() {
        view.printBonusNumberMessage();
        AbstractTemplate<LottoNumber> template = new AbstractTemplate<>() {
            @Override
            protected LottoNumber parse(String param) {
                return Parser.parsingStringToLottoNumber(param);
            }

            @Override
            protected LottoNumber retry() {
                return inputBonusNumber();
            }
        };
        return template.run(scanner.nextLine());
    }

    private LottoWinnerDto inputLottoWinner(Lotto winNumbers) {
        AbstractTemplate<LottoWinnerDto> template = new AbstractTemplate<>() {
            @Override
            protected LottoWinnerDto parse(String param) {
                return new LottoWinnerDto(winNumbers, inputBonusNumber());
            }

            @Override
            protected LottoWinnerDto retry() {
                return inputLottoWinner(winNumbers);
            }
        };
        return template.run("");
    }
}
