package model;

import model.constant.LottoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private static Lotto createLotto() {

        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers(); //1~45개 공들이 들어있따..

        Collections.shuffle(lottoNumbers); // 섞는다.. 6개를 뽑아서 로또라인으로 감싸서 리턴한다..
        return new Lotto(lottoNumbers.stream()
                .limit(LottoInfo.LOTTO_NUMBER_COUNT.valueOf())
                .collect(Collectors.toList()));
    }

    public static LottoTicket createLottoTicket(int amount) { // 여러개 로또를 만들어서 리턴한다
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount / LottoInfo.LOTTO_PRICE.valueOf(); i++) {
            lottoList.add(createLotto());
        }

        return new LottoTicket(lottoList, LottoInfo.LOTTO_PRICE.valueOf() * lottoList.size());
    }

    //public static int getLottoPrice() {
    //    return LottoInfo.LOTTO_PRICE.valueOf();
    //}

}
