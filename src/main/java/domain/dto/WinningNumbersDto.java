package domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
