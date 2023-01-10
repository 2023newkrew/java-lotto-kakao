package lotto.domain.lotto.ticket;

import lotto.domain.lotto.LottoNumber;
import util.ListUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<LottoNumber> lottoNumbers) {
        if (ListUtils.hasDuplicates(lottoNumbers)) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }
}
