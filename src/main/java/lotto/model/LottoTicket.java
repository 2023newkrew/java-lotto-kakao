package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ListUtil;

public class LottoTicket {
    private static final Integer LOTTO_TICKET_LENGTH = 6;
    private static final Integer LOTTO_NUMBER_LOWER_BOUNDARY = 1;
    private static final Integer LOTTO_NUMBER_UPPER_BOUNDARY = 45;

    private final List<LottoNumber> lottoTicket;

    public LottoTicket() {
        List<Integer> numbers = ListUtil.getFrontSubListAndSort(
                ListUtil.createShuffledNumbers(LOTTO_NUMBER_LOWER_BOUNDARY, LOTTO_NUMBER_UPPER_BOUNDARY), LOTTO_TICKET_LENGTH
        );
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        validateLottoTicketLength(lottoNumbers);
        lottoTicket = lottoNumbers;
    }

    public LottoTicket(List<LottoNumber> lottoTicket) {
        validateLottoTicketLength(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateLottoTicketLength(List<LottoNumber> input) {
        if (input == null || input.size() != LOTTO_TICKET_LENGTH) {
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_LENGTH);
        }
    }

    public boolean contains(LottoNumber number) {
        return lottoTicket.contains(number);
    }

    public Integer countOverlappingNumber(LottoTicket lottoTicket) {
        return Math.toIntExact(
                this.lottoTicket.stream()
                        .filter(lottoTicket::contains)
                        .count()
        );
    }

    public List<LottoNumber> getLottoTicket() {
        return lottoTicket;
    }
}
