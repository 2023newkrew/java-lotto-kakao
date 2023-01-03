package lotto.domain;

import java.util.ArrayList;

public class LottoTicket {

    private final ArrayList<Integer> lottoNumbers;

    public LottoTicket(ArrayList<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public ArrayList<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }
}
