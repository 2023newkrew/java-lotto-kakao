package lotto.domain;

import static lotto.constant.ExceptionMessages.INVALID_DUPLICATED_LOTTO_NUMBER;
import static lotto.constant.ExceptionMessages.INVALID_LOTTO_SIZE;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_SIZE = 6;
    private static final List<Integer> numberPool =
            IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<Integer> numbers) {
        validateLotto(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public static Lotto manualGenerate(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static List<Lotto> manualGenerateByNumbersList(List<List<Integer>> numbersList) {
        return numbersList.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public static Lotto autoGenerate() {
        Collections.shuffle(numberPool);
        return new Lotto(numberPool.subList(0, 6));
    }

    public static List<Lotto> autoGenerateByAmounts(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, amount).forEach((i) -> lottos.add(autoGenerate()));
        return lottos;
    }

    private void validateLotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicated(numbers);
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE);
        }
    }

    private void validateDuplicated(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_LOTTO_NUMBER);
        }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
