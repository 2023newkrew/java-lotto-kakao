package lotto.model;

import lotto.LottoSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Issuer {

    private static final List<Integer> numberPool = new ArrayList<>();

    static {
        for (int i = LottoSettings.MIN_RANGE.getValue(); i <= LottoSettings.MAX_RANGE.getValue(); i++) {
            numberPool.add(i);
        }
    }

    private static Lotto issue() {
        Collections.shuffle(numberPool);
        return new Lotto(numberPool.subList(0, LottoSettings.MAX_LENGTH.getValue()));
    }

    public static LottoList issue(Integer count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(issue());
        }
        return new LottoList(lottoList);
    }
}
