package lotto.domain;

import java.util.ArrayList;

public class LottoTicket {

    private final ArrayList<Integer> lottoNumbers;
    public LottoTicket() {
        // 6개 생성
        this.lottoNumbers = new ArrayList<>();
    }


    public ArrayList<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }
}
