package domain.lotto.number;

import domain.dto.WinningNumbersDto;

import java.util.List;

public class LottoNumbersMaker {
    public List<Integer> makeNumbers(final WinningNumbersDto.NumberGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
