package lotto.domain;

import lotto.config.LottoConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numList) {
        if(numList.size() != LottoConfig.FIXED_SIZE){
            throw new RuntimeException("로또 수의 개수가 잘못되었습니다.");
        }
        if (numList.stream().distinct().count() != LottoConfig.FIXED_SIZE) {
            throw new RuntimeException("로또 수에 중복된 수가 있습니다.");
        }
        this.numbers = numList.stream()
                .map(LottoNumber::new)
                .sorted(LottoNumber::compare)
                .collect(Collectors.toList());
    }

    public Lotto(int... nums) {
        this(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public int compare(Lotto other) {
        return (int) numbers.stream().filter(other.numbers::contains).count();
    }

    public boolean hasBonus(LottoNumber other) {
        return numbers.contains(other);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
