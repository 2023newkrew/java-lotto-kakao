import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    Lotto() {
        this.numbers = LottoGenerator.run();
    }

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public LottoStatus getPlace(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        Integer correctWinningNumberCount = 0;
        for(LottoNumber number : numbers) {
            if (winningNumbers.contains(number))
                correctWinningNumberCount++;
        }
        return LottoStatus.getStatus(correctWinningNumberCount, numbers.contains(bonusNumber));
    }

    static public Lotto ofNumbers(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}
