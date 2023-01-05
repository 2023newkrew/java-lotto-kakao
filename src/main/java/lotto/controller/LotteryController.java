package lotto.controller;

import java.util.List;
import lotto.domain.LotteryChecker;
import lotto.domain.Player;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import lotto.domain.lotterynumber.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LotteryController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Player player;
    private LotteryChecker lotteryChecker;

    public void run() {
        initialize();
        sellLotteryTicket();
        printLotteryTicket();
        drawWinningNumbers();
        checkLotteryTicket();
        printResult();
    }

    private void initialize() {
        player = new Player();
    }

    private void sellLotteryTicket() {
        setPlayerBudget();
        setPlayerSelfPickCount();
        sellSelfPicks();
        sellQuickPicks();
    }

    private void setPlayerBudget() {
        try {
            outputView.printReadBudget();
            int budget = inputView.readBudget();
            player.setBudget(budget);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            setPlayerBudget();
        }
    }

    private void setPlayerSelfPickCount() {
        try {
            outputView.printReadSelfPickCount();
            int selfPickCount = inputView.readSelfPickCount();
            player.setSelfPickCount(selfPickCount);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            setPlayerSelfPickCount();
        }
    }

    private void sellSelfPicks() {
        try {
            outputView.printReadSelfPickNumbers();
            List<List<Integer>> selfPickNumbers = inputView.readSelfPickNumbers(player.getSelfPickCount());
            player.buySelfPicksWith(selfPickNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            sellSelfPicks();
        }
    }

    private void sellQuickPicks() {
        player.buyQuickPicks();
    }

    private void printLotteryTicket() {
        outputView.printLotteryTicket(player.getSelfPickCount(), player.getLotteryTicket());
    }

    private void drawWinningNumbers() {
        LotteryNumberCombination winningLotteryNumberCombination = drawWinningLotteryNumberCombination();
        WinningNumbers winningNumbers = fillBonusNumber(winningLotteryNumberCombination);
        lotteryChecker = new LotteryChecker(winningNumbers);
    }

    private LotteryNumberCombination drawWinningLotteryNumberCombination() {
        try {
            outputView.printReadWinningLotteryNumberCombination();
            List<Integer> winningNumberCombination = inputView.readWinningLotteryNumberCombination();
            return new LotteryNumberCombination(winningNumberCombination);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return drawWinningLotteryNumberCombination();
        }
    }

    private WinningNumbers fillBonusNumber(LotteryNumberCombination winningLotteryNumberCombination) {
        try {
            outputView.printReadBonusNumber();
            return new WinningNumbers(winningLotteryNumberCombination, inputView.readBonusNumber());
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return fillBonusNumber(winningLotteryNumberCombination);
        }
    }

    private void checkLotteryTicket() {
        player.checkTicket(lotteryChecker);
    }

    private void printResult() {
        outputView.printResult(player.getRankCounts(), player.getYield());
    }
}
