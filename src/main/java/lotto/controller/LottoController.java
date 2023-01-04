package lotto.controller;

import java.util.*;
import java.util.stream.Collectors;

import lotto.model.number.GoalNumber;
import lotto.model.number.LottoNumber;
import lotto.model.statistic.LottoStatistic;
import lotto.view.console.Console;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoController {

    private static final Integer LOTTO_PRICE = 1000;
    private final OutputView outputView;
    private final InputView inputView;
    private final LottoStatistic statistics = new LottoStatistic();

    private List<LottoNumber> lottoNumberList;

    private Integer numberOfLotto;
    private GoalNumber goalNumber;

    public LottoController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        setLottoNumberListFromUser();
        outputView.printLottos(lottoNumberList);
        setLottoGoalNumberFromUser();
        setStatistics();
        outputView.printStatistics(statistics, getRate());
    }

    private double getRate() {
        Long sumOfPrize = statistics.getSumOfPrize();
        int sumOfLottoPrice = LOTTO_PRICE * numberOfLotto;

        return (double) sumOfPrize / sumOfLottoPrice;
    }

    private void setStatistics() {
        lottoNumberList.stream()
                .map(lotto -> goalNumber.getLottoResultByCompareLotto(lotto))
                .forEach(statistics::raiseCount);
    }

    private void setLottoNumberListFromUser() {
        outputView.askForMoneyToBuyLotto();
        numberOfLotto = parseNumberOfLottos(inputView.getInteger());
        outputView.printNumberOfLotto(numberOfLotto);
        lottoNumberList = createLottoList(numberOfLotto);
    }

    private void setLottoGoalNumberFromUser() {
        outputView.askForLastGoalNumbers();
        List<Integer> goalNumbers = getGoalNumbers();
        outputView.askForBonusBall();
        Integer bonusBall = inputView.getInteger();

        goalNumber = new GoalNumber(goalNumbers, bonusBall);
    }

    private Integer parseNumberOfLottos(Integer input) {
        return input / LOTTO_PRICE;
    }

    private List<LottoNumber> createLottoList(int numberOfLotto) {
        List<LottoNumber> list = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            list.add(LottoNumber.create());
        }
        return list;
    }

    private List<Integer> getGoalNumbers() {
        String input = inputView.getString();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
