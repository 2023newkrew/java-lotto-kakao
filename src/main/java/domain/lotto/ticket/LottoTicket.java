package domain.lotto.ticket;

import domain.lotto.LottoConstant;
import domain.lotto.generator.LottoNumberGenerator;
import domain.lotto.generator.RandomNumberGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    private final List<LottoNumber> lottoNumbers;

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
                .toString();
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.isEmpty())
            throw new NullPointerException();

        validateNumberDuplication(lottoNumbers);
        validateNumberRange(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(new TreeSet<>(lottoNumbers));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateNumberDuplication(List<LottoNumber> lottoNumbers) {
        HashSet<LottoNumber> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoConstant.LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
    }

    private void validateNumberRange(List<LottoNumber> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number.getNumber() < LottoConstant.MIN_LOTTO_NUMBER || number.getNumber() > LottoConstant.MAX_LOTTO_NUMBER)
                        throw new IllegalArgumentException();
                });
    }
}
