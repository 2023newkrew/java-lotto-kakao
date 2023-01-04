package domain;

import common.constant.Constants;
import common.state.Result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    private static final int START_INDEX = 0;
    private static final List<LottoNumber> WHOLE_NUMBERS = IntStream.rangeClosed(Constants.MINIMUM, Constants.MAXIMUM)
            .boxed().map(number -> new LottoNumber(number))
            .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto getAutoLotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<LottoNumber> numbers = new ArrayList<>(WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + Constants.LENGTH));
        numbers.sort(new Comparator<LottoNumber>() {
            @Override
            public int compare(LottoNumber o1, LottoNumber o2) {
                return o1.number - o2.number;
            }
        });
        return new Lotto(numbers);
    }

    public static Lotto getManualLotto(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(Constants.DELIMITER))
                .map(inputString -> new LottoNumber(inputString))
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public Result getResult(WinningLotto winningLotto, LottoNumber bonusNumber) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int count = (int) numbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
        return Result.of(count, isBonus(bonusNumber));
    }

    public boolean isBonus(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public String getLottoNumbers() {
        return numbers.toString();
    }
}
