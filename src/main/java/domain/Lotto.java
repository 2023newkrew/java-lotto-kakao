package domain;

import common.constant.Constants;
import common.state.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    private static final int START_INDEX = 0;
    private static final List<Integer> WHOLE_NUMBERS = IntStream.rangeClosed(Constants.MINIMUM, Constants.MAXIMUM)
            .boxed()
            .collect(Collectors.toList());

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto getAutoLotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<Integer> numbers = new ArrayList<>(WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + Constants.LENGTH));
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public static Lotto getManualLotto(String input) {
        List<Integer> numbers = Arrays.stream(input.split(Constants.DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public Result getResult(WinningLotto winningLotto, LottoNumber bonusNumber) {
        List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int count = (int) numbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
        return Result.of(count, isBonus(bonusNumber));
    }

    public boolean isBonus(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber.number);
    }

    public String getLottoNumbers() {
        return numbers.toString();
    }
}
