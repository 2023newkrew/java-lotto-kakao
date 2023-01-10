package lotto.domain.lotto.ticket;

import lotto.domain.lotto.LottoNumber;
import util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
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
