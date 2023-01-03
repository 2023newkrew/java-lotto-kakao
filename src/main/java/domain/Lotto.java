package domain;

import dto.WinningLotto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final Integer LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto() {
        this.numbers = LottoGenerator.run();
    }

    static public Lotto ofNumbers(List<Integer> numbers) {
        validateLotto(numbers);
        return new Lotto(numbers);
    }

    static private void validateLotto(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 6개이어야 합니다.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 중복이 불가합니다.");
        }
    }

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public LottoRank getRank(WinningLotto winningLotto) {
        Integer matchCount = 0;
        for(LottoNumber number : numbers) {
            if (winningLotto.getLotto().has(number)) {
                matchCount++;
            }
        }
        return LottoRank.getRank(matchCount, numbers.contains(winningLotto.getBonusNumber()));
    }

    public Boolean has(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
