package lotto.model;

import java.util.*;

public class LottoNumber {

    private static final String ERR_LOTTO_NUMBER_OUT_OF_RANGE = "로또 숫자는 1부터 45 사이의 숫자여야 합니다.";
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    private final int number;

    private LottoNumber(int number) {
        if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException(ERR_LOTTO_NUMBER_OUT_OF_RANGE);
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static LottoNumber from(int number) {
        if (!lottoNumbers.containsKey(number)) {
            lottoNumbers.put(number, new LottoNumber(number));
        }

        return lottoNumbers.get(number);
    }

    public static List<LottoNumber> of(List<Integer> numbers) {
        List<LottoNumber> instances = new ArrayList<>();
        for (Integer number : numbers) {
            instances.add(from(number));
        }

        return instances;
    }
}
