package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.SingleLottoNumber;

public class RandomLottoGenerator {

    private RandomLottoGenerator() {
    }

    private static final List<SingleLottoNumber> lottoBalls =
            IntStream.rangeClosed(SingleLottoNumber.MIN_LOTTO_NUMBER, SingleLottoNumber.MAX_LOTTO_NUMBER)
                    .mapToObj(SingleLottoNumber::new)
                    .collect(Collectors.toList());

    public static List<Lotto> generateLottoList(int size) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<SingleLottoNumber> chosen = getRandomLottoNumbers();
            lottoList.add(new Lotto(chosen));
        }

        return lottoList;
    }

    private static List<SingleLottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(Lotto.LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }
}
