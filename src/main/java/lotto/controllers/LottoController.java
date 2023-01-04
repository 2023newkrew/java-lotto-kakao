package lotto.controllers;

import static lotto.common.LottoConfiguration.LOTTO_PRICE;

import java.util.List;
import lotto.models.Goal;
import lotto.models.Lotto;
import lotto.common.LottoGenerator;
import lotto.models.LottoStatistics;
import lotto.views.Console;
import lotto.views.InputView;
import lotto.views.OutputView;

public class LottoController {
    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        Integer budget = getBudget();
        Integer numberOfLottos = parseNumberOfLottos(budget);
        List<Lotto> lottos = createLottoList(numberOfLottos);
        Goal goal = getGoal();
        showStatistics(goal, lottos, budget);
    }

    private Integer getBudget() {
        return outputView.requestUntilSuccess(this::getBudgetLogic);
    }

    private Integer getBudgetLogic() {
        outputView.askForMoneyToBuyLotto();
        return inputView.getInteger();
    }

    private Integer parseNumberOfLottos(int budget) {
        Integer numberOfLottos = budget / LOTTO_PRICE;
        outputView.printNumberOfLotto(numberOfLottos);
        return numberOfLottos;
    }

    private List<Lotto> createLottoList(int numberOfLotto) {
        List<Lotto> lottos = LottoGenerator.createLottos(numberOfLotto);
        outputView.printLottos(lottos);
        return lottos;
    }

    private Goal getGoal() {
        return outputView.requestUntilSuccess(this::getGoalLogic);
    }

    private Goal getGoalLogic() {
        List<Integer> goalNumbers = getGoalNumbers();
        Integer bonusBall = getBonusBall();
        return new Goal(goalNumbers, bonusBall);
    }

    private List<Integer> getGoalNumbers() {
        return outputView.requestUntilSuccess(this::getGoalNumbersLogic);
    }

    private List<Integer> getGoalNumbersLogic() {
        outputView.askForLastGoalNumbers();
        return inputView.getIntegerList();
    }

    private Integer getBonusBall() {
        return outputView.requestUntilSuccess(this::getBonusBallLogic);
    }

    private Integer getBonusBallLogic() {
        outputView.askForBonusBall();
        return inputView.getInteger();
    }

    private void showStatistics(Goal goal, List<Lotto> lottos, Integer budget) {
        LottoStatistics statistics = new LottoStatistics(goal, lottos, budget);
        outputView.printStatistics(statistics);
    }
}
