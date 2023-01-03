package lotto.controllers;

import static lotto.common.LottoConfiguration.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.models.Goal;
import lotto.models.Lotto;
import lotto.models.LottoGenerator;
import lotto.models.LottoStatistics;
import lotto.views.Console;
import lotto.views.InputView;
import lotto.views.OutputView;

public class LottoController {
    private final OutputView outputView;
    private final InputView inputView;
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public LottoController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        Integer budget = getBudget();
        Integer numberOfLottos = parseNumberOfLottos(budget);
        List<Lotto> lottos = createLottoList(numberOfLottos);
        Goal goal = getGoal();
        LottoStatistics statistics = new LottoStatistics(goal, lottos, budget);
        outputView.printStatistics(statistics);
    }

    private Integer getBudget() {
        outputView.askForMoneyToBuyLotto();
        return inputView.getInteger();
    }

    private Integer parseNumberOfLottos(int budget) {
        Integer numberOfLottos = budget / LOTTO_PRICE;
        outputView.printNumberOfLotto(numberOfLottos);
        return numberOfLottos;
    }

    private List<Lotto> createLottoList(int numberOfLotto) {
        List<Lotto> list = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            list.add(lottoGenerator.createLotto());
        }
        outputView.printLottos(list);
        return list;
    }

    private Goal getGoal() {
        List<Integer> goalNumbers = getGoalNumbers();
        Integer bonusBall = getBonusBall();
        return new Goal(goalNumbers, bonusBall);
    }

    private List<Integer> getGoalNumbers() {
        outputView.askForLastGoalNumbers();
        String input = inputView.getString();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private Integer getBonusBall() {
        outputView.askForBonusBall();
        return inputView.getInteger();
    }
}
