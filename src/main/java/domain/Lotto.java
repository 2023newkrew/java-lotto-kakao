package domain;

import dto.WinningLotto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.ErrorMessage.DUPLICATED_LOTTO_NUMBERS;
import static utils.ErrorMessage.LOTTO_HAS_SIX_NUMBER;

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
            throw new IllegalArgumentException(LOTTO_HAS_SIX_NUMBER.getMessage());
        }
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBERS.getMessage());
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
