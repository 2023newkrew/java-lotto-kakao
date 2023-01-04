package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumbers;
import lotto.domain.SingleLottoNumber;

public class RandomLottoGenerator {

    private RandomLottoGenerator() {
    }

    private static final List<SingleLottoNumber> lottoBalls =
            IntStream.rangeClosed(SingleLottoNumber.MIN_LOTTO_NUMBER, SingleLottoNumber.MAX_LOTTO_NUMBER)
                    .mapToObj(SingleLottoNumber::new)
                    .collect(Collectors.toList());

    public static List<LottoNumbers> generateLottos(int size) {
        List<LottoNumbers> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<SingleLottoNumber> chosen = getRandomLottoNumbers();
            lottoNumbers.add(new LottoNumbers(chosen));
        }

        return lottoNumbers;
    }

    private static List<SingleLottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LottoNumbers.LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }
}
