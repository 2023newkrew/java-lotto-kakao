package domain.lotto.ticket.generator;

import domain.lotto.LottoNumber;
import domain.lotto.ticket.LottoTicket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketRandomGenerator implements LottoTicketGenerator {

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumberCandidates = IntStream.range(LottoNumber.MINIMUM_VALUE, LottoNumber.MAXIMUM_VALUE)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        Collections.shuffle(lottoNumberCandidates);
        return lottoNumberCandidates.subList(0, LottoTicket.LOTTO_NUMBERS_SIZE);
    }
}
