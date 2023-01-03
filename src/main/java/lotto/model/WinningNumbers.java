package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final LottoTicket sixNumbers;
    private final LottoValue bonusNumber;

    public WinningNumbers(String sixNumbersString, String bonusNumberString) {
        List<String> sixNumbersList = new ArrayList<>(List.of(sixNumbersString.split(", |,")));
        sixNumbers = parseSixNumbers(sixNumbersList);
        bonusNumber = parseBonusNumber(bonusNumberString);
    }

    private LottoValue parseBonusNumber(String bonusNumberString) {
        LottoValue bonusNumber = new LottoValue(Integer.parseInt(bonusNumberString));
        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    private void validateBonusNumber(LottoValue bonusNumber) {
        if (sixNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 여섯 개의 숫자와 중복되어서는 안됩니다.");
        }
    }

    private LottoTicket parseSixNumbers(List<String> sixNumbersList) {
        return new LottoTicket(sixNumbersList.stream().map(
                        numberString -> new LottoValue(Integer.parseInt(numberString)))
                .collect(Collectors.toList()));
    }

    public Grade matchValues(LottoTicket lottoTicket) {
        int sixCount = (int) lottoTicket.getLottoValues()
                .stream()
                .filter(sixNumbers::contains)
                .count();

        int bonusCount = 0;
        if (sixCount == 5 && lottoTicket.contains(bonusNumber)) {
            bonusCount++;
        }

        return Grade.getGrade(sixCount + 10 * bonusCount);
    }
}
