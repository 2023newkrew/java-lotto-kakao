package lotto.domain;

import lotto.config.LottoConfig;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> numList) {
        numbers = numList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));

        if(numbers.size() != LottoConfig.LOTTO_SIZE){
            throw new RuntimeException("로또 수의 개수가 잘못되었거나 중복된 수가 있습니다.");
        }
    }

    public Lotto(int... nums) {
        this(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public int getMatchCount(Lotto other) {
        Set<LottoNumber> matchedSet = new TreeSet<>(numbers);
        matchedSet.retainAll(other.numbers);
        return matchedSet.size();
    }

    public boolean hasBonus(LottoNumber other) {
        return numbers.contains(other);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
