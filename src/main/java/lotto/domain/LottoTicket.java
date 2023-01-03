package lotto.domain;

import java.util.ArrayList;

public class LottoTicket {
    private static final int LOTTO_TICKET_SIZE = 6;
    private final ArrayList<Integer> lottoNumbers;

    public LottoTicket(ArrayList<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        lottoNumberCountCheck();
    }

    public ArrayList<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }

    private void lottoNumberCountCheck() {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 6개가 아닙니다");
        }
    }
}