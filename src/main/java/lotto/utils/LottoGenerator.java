package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.UserLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private LottoGenerator() {
    }

    private static final List<LottoNumber> lottoBalls =
            IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                    .mapToObj(LottoNumber::from)
                    .collect(Collectors.toList());

    public static List<UserLotto> generateRandomLottos(int size) {
        List<UserLotto> randomLottos = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<LottoNumber> chosen = getRandomLottoNumbers();
            LottoNumbers lottoNumbers = new LottoNumbers(chosen);
            randomLottos.add(new UserLotto(lottoNumbers));
        }

        return randomLottos;
    }

    private static List<LottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LottoNumbers.LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<UserLotto> generateManualLottos(List<List<Integer>> numbersGroup) {
        return numbersGroup.stream()
                .map(LottoGenerator::generateManualLotto)
                .collect(Collectors.toList());
    }

    private static UserLotto generateManualLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new UserLotto(new LottoNumbers(lottoNumbers));
    }
}
