package lotto.model;

import java.util.List;

public class LottoPublisher {
    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoPublisher(LottoNumbers lottoNumbers) {
        this.randomNumbersGenerator = new RandomNumbersGenerator(lottoNumbers);
    }

    public LottoTicket publishNewAutomaticLotto() {
        return new LottoTicket(this.randomNumbersGenerator.getOrderedNumbers());
    }

    public LottoTicket publishNewManualLotto(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }
}
