package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.util.ListUtil;

public class LottoTicket {
    private static final Integer LOTTO_TICKET_LENGTH = 6;
    private static final String SPLIT_DELIMITER = ",";

    private final List<LottoNumber> lottoTicket;

    public LottoTicket() {
        List<Integer> numbers = ListUtil.getFrontSubListAndSort(
                ListUtil.shuffleNumbers(LottoNumber.getLottoNumbers()), LOTTO_TICKET_LENGTH
        );
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        lottoTicket = lottoNumbers;
    }

    public LottoTicket(List<LottoNumber> lottoTicket) {
        validateLottoTicketLength(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public LottoTicket(String lottoTicket) {
        this(Arrays.stream(lottoTicket.split(SPLIT_DELIMITER))
                .map(LottoNumber::from)
                .collect(Collectors.toList()));
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
