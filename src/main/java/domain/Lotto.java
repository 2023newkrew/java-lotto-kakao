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

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public Rank getRank(WinningLotto winningLotto) {
        Long correctWinningNumberCount = numbers.stream().filter(winningLotto.getLotto()::isIn).count();
        return Rank.getLottoRank(correctWinningNumberCount.intValue(), numbers.contains(winningLotto.getBonusNumber()));
    }

    public Boolean isIn(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
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

    @Override
    public String toString() {
        return numbers.toString();
    }
}
