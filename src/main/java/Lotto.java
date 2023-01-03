import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final Integer LOTTO_SIZE = 6;
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

    public LottoStatus getPlace(Lotto winningNumbers, LottoNumber bonusNumber) {
        Integer correctWinningNumberCount = 0;
        for(LottoNumber number : numbers) {
            if (winningNumbers.numbers.contains(number))
                correctWinningNumberCount++;
        }
        return LottoStatus.getStatus(correctWinningNumberCount, numbers.contains(bonusNumber));
    }

    static public Lotto ofNumbers(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        return new Lotto(numbers);
    }

    static private void validateLottoNumbers(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 6개이어야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 중복이 불가합니다.");
        }
    }
}
