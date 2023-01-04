package lotto.controller;

import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lotto.common.exception.InvalidInputException;
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
        outputView.printLotto(lottoNumberList);
        setLottoGoalNumberFromUser();
        setStatistics();
        outputView.printStatistics(statistics, getRate());
    }

    private double getRate() {
        Long sumOfPrize = statistics.getSumOfPrize();
        int sumOfLottoPrice = LOTTO_PRICE * numberOfLotto;
        if(sumOfLottoPrice < 0) {
            return 0;
        }
        return (double) sumOfPrize / sumOfLottoPrice;
    }

    private void setStatistics() {
        lottoNumberList.stream()
                .map(lotto -> goalNumber.getLottoResultByCompareLotto(lotto))
                .forEach(statistics::raiseCount);
    }

    private void setLottoNumberListFromUser() {
        try {
            outputView.askForMoneyToBuyLotto();
        } catch(Error e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        numberOfLotto = parseNumberOfLotto(inputView.getInteger());
        outputView.askForNumberOfLottoWithManual();
        Integer numberOfLottoWithManual = parseNumberOfLottoWithManual(inputView.getInteger());
        if(numberOfLottoWithManual > 0) {
            outputView.askForLottoNumberWithManual();
        }
        lottoNumberList = createLottoList(numberOfLotto, numberOfLottoWithManual);
        outputView.printNumberOfLotto(numberOfLotto, numberOfLottoWithManual);
    }

    private void setLottoGoalNumberFromUser() {
        outputView.askForLastGoalNumbers();
        List<Integer> goalNumbers = getLottoNumbersFromUser();
        outputView.askForBonusBall();
        Integer bonusBall = inputView.getInteger();

        goalNumber = new GoalNumber(goalNumbers, bonusBall);
    }

    private Integer parseNumberOfLotto(Integer input) {
        return input / LOTTO_PRICE;
    }

    private Integer parseNumberOfLottoWithManual(Integer input) {
        return Math.min(input, numberOfLotto);
    }

    private List<LottoNumber> createLottoList(int numberOfLotto, int numberOfLottoWithManual) {
        List<LottoNumber> list = createLottoListWithManual(numberOfLottoWithManual);
        for (int i = 0; i < numberOfLotto - numberOfLottoWithManual; i++) {
            list.add(LottoNumber.create());
        }
        return list;
    }

    private List<LottoNumber> createLottoListWithManual(int numberOfLottoWithManual) {
        List<LottoNumber> list = new ArrayList<>();
        for (int i = 0; i < numberOfLottoWithManual; i++) {
            list.add(LottoNumber.create(getLottoNumbersFromUser()));
        }
        return list;
    }

    private List<Integer> getLottoNumbersFromUser() {
        return Arrays.stream(inputView.getString().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
