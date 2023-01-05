package domain.lotto.ticket;

import domain.lotto.LottoNumber;
import util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
        validateSize();
        validateDuplicates();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateSize() {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates() {
        if (ListUtils.hasDuplicates(lottoNumbers)) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }
}
