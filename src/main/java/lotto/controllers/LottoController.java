package lotto.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.models.Lotto;
import lotto.models.LottoGenerator;
import lotto.models.LottoResult;
import lotto.views.*;

public class LottoController {
    private final OutputView outputView;
    private final InputView inputView;
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public LottoController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        outputView.askForMoneyToBuyLotto();
        Integer numberOfLottos = parseNumberOfLottos(inputView.getInteger());
        List<Lotto> lottoList = createLottoList(numberOfLottos);
        outputView.printLottos(lottoList);
        outputView.askForLastGoalNumbers();
        List<Integer> goalNumbers = getGoalNumbers();
        outputView.askForBonusBall();
        Integer bonusBall = inputView.getInteger();
        // TODO: get statistics

        outputView.printStatistics(new HashMap<>(), 0.00);
    }

    private Integer parseNumberOfLottos(Integer input) {
        return input / 1000;
    }

    private List<Lotto> createLottoList(int numberOfLotto) {
        List<Lotto> list = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            list.add(lottoGenerator.createLotto());
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
