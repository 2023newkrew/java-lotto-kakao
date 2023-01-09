package lotto.controller;

import lotto.model.ranking.AnalysisResult;
import lotto.model.ranking.RankingCounts;
import lotto.model.store.*;
import lotto.model.ranking.WinningNumbers;
import lotto.model.ticket.LottoTicket;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoSimulator {

    private final LottoStore store;

    private final LottoInputView inputView;

    private final LottoOutputView outputView;

    public static LottoSimulator create(Money lottoPrice, LottoInputView inputView, LottoOutputView outputView) {
        LottoMachine lottoMachine = LottoMachine.create(lottoPrice);
        LottoStore store = LottoStore.create(lottoMachine);

        return new LottoSimulator(store, inputView, outputView);
    }

    private LottoSimulator(LottoStore store, LottoInputView inputView, LottoOutputView outputView) {
        this.store = store;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseResult purchaseResult = createPurchaseResult();
        WinningNumbers winningNumbers = inputView.inputWinningNumbers();
        AnalysisResult analysisResult = analyze(purchaseResult, winningNumbers);
        outputView.printAnalysisResult(analysisResult);
    }

    private PurchaseResult createPurchaseResult() {
        Money money = inputView.inputMoney();
        long maxCount = store.getPurchasableCount(money);
        long manualCount = inputView.inputManualCount(maxCount);
        LottoTicket manualTicket = inputView.inputManualLottos(manualCount);
        PurchaseResult purchaseResult = store.buyManually(money, manualTicket);
        outputView.printPurchaseResult(manualCount, purchaseResult);

        return purchaseResult;
    }

    private static AnalysisResult analyze(PurchaseResult purchaseResult, WinningNumbers winningNumbers) {
        LottoTicket ticket = purchaseResult.getTicket();
        LottoReceipt receipt = purchaseResult.getReceipt();
        RankingCounts rankingCounts = ticket.judge(winningNumbers);

        return AnalysisResult.from(rankingCounts, receipt);
    }
}
