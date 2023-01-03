package domain.lotto;

import domain.lotto.ticket.LottoTicket;
import util.ListUtils;

import java.util.Collections;
import java.util.List;

public class LottoWinningNumber {

    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinningNumber(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        validateSize(lottoNumbers);
        validateDuplicates(lottoNumbers, bonusNumber);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LottoTicket.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public void validateDuplicates(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        if (ListUtils.hasDuplicates(lottoNumbers) || lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }
}
