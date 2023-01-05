import buyer.Buyer;
import buyer.BuyerResult;
import lotto.LottoGenerator;
import lotto.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());

        int manualQuantity = buyManually(buyer);
        int autoQuantity = lottoGenerator.autoGenerateRemaining(buyer);

        outputView.printQuantity(manualQuantity, autoQuantity);
        outputView.printLotteries(buyer.getLottos());

        String winningNumbersAsString = inputView.getWinningNumbersInput();
        int bonusNumber = inputView.getBonusNumberInput();

        WinningLotto winningLotto = new WinningLotto(winningNumbersAsString, bonusNumber);
        BuyerResult buyerResult = winningLotto.getResult(buyer.getLottos());

        outputView.printResult(buyerResult);
    }

    private int buyManually(Buyer buyer) {
        int manualQuantity = inputView.getManualLottoQuantityInput();
        if (!lottoGenerator.canBuyNLottos(buyer, manualQuantity)) throw new IllegalArgumentException("금액이 부족합니다!");
        List<String> manualNumberStrings = inputView.getManualNumbersInput(manualQuantity);
        lottoGenerator.manuallyGenerate(buyer, manualNumberStrings);
        return manualQuantity;
    }
}
