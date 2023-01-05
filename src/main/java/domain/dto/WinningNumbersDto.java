package domain.dto;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbersDto {
    private List<Integer> lottoNumbers;
    private Integer bonusNumber;

    public WinningNumbersDto(List<Integer> lottoNumbers, int bonusNumber) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
}
