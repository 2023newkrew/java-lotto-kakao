package lotto.model.service;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoNumbers;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class LottoPublisher {
    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoPublisher(LottoNumbers lottoNumbers) {
        this.randomNumbersGenerator = new RandomNumbersGenerator(lottoNumbers);
    }

    public LottoTicket publishLotto() {
        return new LottoTicket(this.randomNumbersGenerator.getOrderedNumbers());
    }

    public LottoTicket publishLotto(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }
}
