package lotto.model.ticket;

import java.util.HashMap;

public class LottoNumbers {
    private static final HashMap<Integer, LottoNumber> NUMBERS = new HashMap<>();

    static {
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            LottoNumbers.NUMBERS.put(i, new LottoNumber(i));
        }
    }

    public static LottoNumber get(int number) {
        return LottoNumbers.NUMBERS.get(number);
    }
}
