package domain.lotto.ticket;

import domain.lotto.LottoMetaData;

import java.util.*;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public LottoTicket(List<Integer> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.isEmpty())
            throw new NullPointerException();

        validateNumberDuplication(lottoNumbers);
        validateNumberRange(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(new TreeSet<>(lottoNumbers));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateNumberDuplication(List<Integer> lottoNumbers) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoMetaData.LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
    }

    private void validateNumberRange(List<Integer> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number < LottoMetaData.MIN_LOTTO_NUMBER || number > LottoMetaData.MAX_LOTTO_NUMBER)
                        throw new IllegalArgumentException();
                });
    }
}
