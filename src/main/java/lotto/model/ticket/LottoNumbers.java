package lotto.model.ticket;

import java.util.HashMap;

public class LottoNumbers {
    private final HashMap<Integer, LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this.lottoNumbers = new HashMap<>();
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            this.lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    public LottoNumber get(int number) {
        return this.lottoNumbers.get(number);
    }
}
