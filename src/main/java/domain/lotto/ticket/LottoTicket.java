package domain.lotto.ticket;

import domain.lotto.LottoMetaData;
import domain.lotto.number.LottoNumbers;

import java.util.*;

public class LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoTicket(final List<Integer> lottoNumbers) {
        if (lottoNumbers == null) throw new NullPointerException();
        this.lottoNumbers = new LottoNumbers(new ArrayList<>(new TreeSet<>(lottoNumbers)));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getNumbers();
    }

    @Override
    public String toString() {
        return lottoNumbers.getNumbers().toString();
    }
}
