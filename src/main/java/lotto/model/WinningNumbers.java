package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(String inputSixNumbers, String inputBonusNumber) {
        List<String> sixNumbers = Arrays.stream(
                        inputSixNumbers.split(",")
                ).map(String::trim)
                .collect(Collectors.toList());
        winningTicket = parseSixNumbers(sixNumbers);
        bonusNumber = parseBonusNumber(inputBonusNumber);
    }

    public WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private LottoNumber parseBonusNumber(String inputBonusNumber) {
        LottoNumber bonusNumber = LottoNumber.valueOf(Integer.parseInt(inputBonusNumber));
        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 여섯 개의 숫자와 중복되어서는 안됩니다.");
        }
    }

    private LottoTicket parseSixNumbers(List<String> inputSixNumbers) {
        return new LottoTicket(inputSixNumbers.stream().map(
                        numberString -> LottoNumber.valueOf(Integer.parseInt(numberString)))
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
