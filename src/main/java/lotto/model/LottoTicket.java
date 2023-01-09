package lotto.model;


import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.config.LottoConfig.LOTTO_TICKET_LENGTH;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> input){
        validateLottoTicketLength(input);
        lottoNumbers = input;
    }

    private void validateLottoTicketLength(List<LottoNumber> input){
        if(input == null || input.size() != LOTTO_TICKET_LENGTH){
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_LENGTH);
        }
    }

    public Integer countOverlappingNumber(LottoTicket lottoTicket) {
        return Math.toIntExact(
                lottoNumbers.stream()
                .filter(lottoTicket::contains)
                .count()
        );
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return "[" +
                lottoNumbers.stream()
                .map(LottoNumber::getLottoNumberString)
                .collect(Collectors.joining(", ")) +
                "]";
    }
}
