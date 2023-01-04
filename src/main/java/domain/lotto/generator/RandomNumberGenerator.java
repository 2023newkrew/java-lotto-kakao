package domain.lotto.generator;

import domain.lotto.LottoConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGeneratable {

    @Override
    public List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        for (int i = LottoConstant.MIN_LOTTO_NUMBER; i <= LottoConstant.MAX_LOTTO_NUMBER; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0, LottoConstant.LOTTO_NUMBER_SIZE);
    }
}
