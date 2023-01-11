package model;

import java.util.*;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private static Lotto createLotto() {

        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();

        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .collect(Collectors.toSet()));
    }

    public static LottoTicket createLottoTicket(int automaticLottoCount, List<Lotto> lottos) {
        for (int i = 0; i < automaticLottoCount; i++) {
            lottos.add(createLotto());
        }
        return new LottoTicket(lottos);
    }
}
