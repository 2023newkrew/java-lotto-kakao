package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoConstant.*;

public class LottoTicketAutoGenerator implements LottoTicketGenerator{

    @Override
    public LottoTicket generate() {
        List<Integer> numbers = IntStream.range(LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = numbers.subList(0, LOTTO_LENGTH)
                .stream().map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoTicket(new ArrayList<>(lottoNumbers));
    }

    @Override
    public List<LottoTicket> generate(int lottoCount) {
        return IntStream.range(0, lottoCount)
                .mapToObj((currentCount) -> generate())
                .collect(Collectors.toList());
    }
}
