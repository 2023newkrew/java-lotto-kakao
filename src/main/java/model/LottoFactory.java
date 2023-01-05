package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_PRICE = 1000;

    private static Lotto createLotto() {

        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();

        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .collect(Collectors.toList()));
    }

    public static LottoTicket createLottoTicket(long purchaseMoney) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseMoney / LOTTO_PRICE; i++) {
            lottoList.add(createLotto());
        }

        return new LottoTicket(lottoList);
    }

}
