package domain;

import dto.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private static final Integer LOTTO_SIZE = 6;
    private static final String ERROR_LOTTO_SIZE = "로또 숫자는 6개이어야 합니다.";
    private static final String ERROR_LOTTO_DUPLICATE = "로또 숫자는 중복이 불가합니다.";
    private static final List<LottoNumber> LOTTO_NUMBER_CANDIDATES = LottoNumber.getAll();

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validateLotto(numbers);
        this.numbers = numbers;
    }

    static public Lotto ofManual(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers
                .stream()
                .map(LottoNumber::new)
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    static public Lotto ofAuto() {
        List<LottoNumber> lottoNumbers = Lotto.generate();
        return new Lotto(lottoNumbers);
    }

    static private List<LottoNumber> generate() {
        Collections.shuffle(LOTTO_NUMBER_CANDIDATES);
        List<LottoNumber> lottoNumbers = new ArrayList<>(LOTTO_NUMBER_CANDIDATES.subList(0, LOTTO_SIZE));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    static private void validateLotto(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }

        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_DUPLICATE);
        }
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
