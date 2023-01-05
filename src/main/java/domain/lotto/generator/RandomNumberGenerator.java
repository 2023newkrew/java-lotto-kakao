package domain.lotto.generator;

import domain.lotto.LottoConstant;
import domain.lotto.ticket.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomNumberGenerator implements NumberGeneratable {

    @Override
    public List<LottoNumber> generate() {
        List<Integer> list = new ArrayList<>();
        for (int i = LottoConstant.MIN_LOTTO_NUMBER; i <= LottoConstant.MAX_LOTTO_NUMBER; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.subList(0, LottoConstant.LOTTO_NUMBER_SIZE)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
