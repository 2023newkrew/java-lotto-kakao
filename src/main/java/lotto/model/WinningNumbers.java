package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(String sixNumbersString, String bonusNumberString) {
        List<String> sixNumbersList = new ArrayList<>(List.of(sixNumbersString.split(", ")));
        winningTicket = parseSixNumbers(sixNumbersList);
        bonusNumber = parseBonusNumber(bonusNumberString);
    }

    private LottoNumber parseBonusNumber(String bonusNumberString) {
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(bonusNumberString));
        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 여섯 개의 숫자와 중복되어서는 안됩니다.");
        }
    }

    private LottoTicket parseSixNumbers(List<String> sixNumbersList) {
        return new LottoTicket(sixNumbersList.stream().map(
                        numberString -> new LottoNumber(Integer.parseInt(numberString)))
                .collect(Collectors.toList()));
    }

    public Grade match(LottoTicket lottoTicket) {
        int matchedCount = (int) winningTicket.stream()
                .filter(lottoTicket::contains)
                .count();

        int bonusCount = 0;
        if (matchedCount == 5 && lottoTicket.contains(bonusNumber)) {
            bonusCount++;
        }

        return Grade.getGrade(matchedCount + 10 * bonusCount);
    }
}
