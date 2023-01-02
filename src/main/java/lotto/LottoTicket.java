package lotto;

import java.util.List;

public class LottoTicket {
    LottoNumbers lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        lottoNumbers = new LottoNumbers(numbers);
    }

    public String getString() {
        return lottoNumbers.getString();
    }
}
