package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketAutoGenerator implements LottoTicketGenerator{
    private final Random random;
    public LottoTicketAutoGenerator(){
        this.random = new Random();
    }

    @Override
    public LottoTicket generate() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();

        while(lottoNumbers.size() < LottoConstant.LOTTO_LENGTH){
            int randomNumber = getRandomNumber();
            lottoNumbers.add(new LottoNumber(randomNumber));
        }
        return new LottoTicket(new ArrayList<>(lottoNumbers));
    }

    @Override
    public List<LottoTicket> generate(int lottoCount) {
        return IntStream.range(0, lottoCount)
                .mapToObj((currentCount) -> generate())
                .collect(Collectors.toList());
    }

    private int getRandomNumber(){
        return random.nextInt(LottoConstant.LOTTO_NUMBER_MAX_VALUE) + 1;
    }
}
