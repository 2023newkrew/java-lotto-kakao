package lotto.controller;

import java.util.*;

import lotto.model.number.GoalNumber;
import lotto.model.number.LottoNumber;
import lotto.model.statistic.LottoStatistic;
import lotto.view.console.Console;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoController {

    private static final Integer LOTTO_PRICE = 1000;
    private static final String DELIMITER = ",";
    private final OutputView outputView;
    private final InputView inputView;
    private final LottoStatistic statistics = new LottoStatistic(LOTTO_PRICE);
    private final List<LottoNumber> lottoNumberList = new ArrayList<>();

//    private Integer numberOfTotalLotto;
    private GoalNumber goalNumber;

    public LottoController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        setLotto();
        setGoal();
        showResult();
    }

    private void setLotto() {
        outputView.askForMoneyToBuyLotto();
        Integer numberOfTotalLotto = getTotalNumberOfLotto(inputView.getInteger());

        outputView.askForNumberOfManualLotto();
        Integer numberOfManualLotto = getNumberOfManualLotto(inputView.getInteger(), numberOfTotalLotto);
        if(numberOfManualLotto > 0) {
            outputView.askForLottoNumberWithManual();
        }

        setLottoList(numberOfTotalLotto, numberOfManualLotto);

        outputView.printNumberOfLotto(numberOfTotalLotto, numberOfManualLotto);
        outputView.printLotto(lottoNumberList);
    }

    private Integer getTotalNumberOfLotto(Integer input) {
        return input / LOTTO_PRICE;
    }

    private Integer getNumberOfManualLotto(Integer input, Integer maxValue) {
        return Math.min(input, maxValue);
    }

    private void setLottoList(int numberOfLotto, int numberOfLottoWithManual) {
        for (int i = 0; i < numberOfLottoWithManual; i++) {
            lottoNumberList.add(LottoNumber.create(inputView.getListOfInteger(DELIMITER)));
        }
        for (int i = numberOfLottoWithManual; i < numberOfLotto; i++) {
            lottoNumberList.add(LottoNumber.create());
        }
    }

    private void setGoal() {
        outputView.askForLastGoalNumbers();
        List<Integer> goalNumbers = inputView.getListOfInteger(DELIMITER);

        outputView.askForBonusBall();
        Integer bonusBall = inputView.getInteger();

        goalNumber = new GoalNumber(goalNumbers, bonusBall);
    }

    private void showResult() {
        lottoNumberList.stream()
                .map(lotto -> goalNumber.getLottoResult(lotto))
                .forEach(statistics::raiseCount);

        outputView.printStatistics(statistics);
    }
}
