package domain.lotto.makingnumbers;

import domain.lotto.LottoConstantData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGeneratable {

    @Override
    public List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        for (int i = LottoConstantData.MIN_LOTTO_NUMBER; i <= LottoConstantData.MAX_LOTTO_NUMBER; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0, LottoConstantData.LOTTO_NUMBER_SIZE);
    }
}
