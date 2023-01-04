package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {
    private RandomLottoGenerator() {
    }

    private static final List<LottoNumber> lottoBalls =
            IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());

    public static List<LottoNumbers> generateLottos(int size) {
        List<LottoNumbers> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<LottoNumber> chosen = getRandomLottoNumbers();
            lottoNumbers.add(new LottoNumbers(chosen));
        }

        return lottoNumbers;
    }

    private static List<LottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LottoNumbers.LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }
}
