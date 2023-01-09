package domain.dto;

import domain.lotto.ticket.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbersDto {
    private List<LottoNumber> lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningNumbersDto(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
