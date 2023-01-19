package domain;

import utils.NumberParser;
import view.InputView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.InputMessage.INPUT_MANUAL_LOTTO;

public class ManualStore implements LottoStore {

    private List<Integer> getManualNumbers() {
        String input = InputView.getManualLottoNumbersInput();
        return NumberParser.splitAndParse(input);
    }

    @Override
    public List<Lotto> buy(Integer amount) {
        System.out.println(INPUT_MANUAL_LOTTO.getMessage());
        return IntStream.range(0, amount)
                .mapToObj((number) -> Lotto.ofNumbers(getManualNumbers()))
                .collect(Collectors.toList());
    }
}
