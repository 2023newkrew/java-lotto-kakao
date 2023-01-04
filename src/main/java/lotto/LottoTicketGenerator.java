package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator implements Generator<LottoTicket> {
    private final Generator<LottoBall> generator;

    public LottoTicketGenerator(Generator<LottoBall> generator) {
        this.generator = generator;
    }

    @Override
    public LottoTicket generate() {
        List<LottoBall> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() != 6) {
            lottoNumbers.add(generateEachBall(lottoNumbers));
        }
        return new LottoTicket(lottoNumbers);
    }

    private LottoBall generateEachBall(List<LottoBall> lottoNumbers) {
        LottoBall generatedBall = generator.generate();
        while (lottoNumbers.contains(generatedBall)) {
            generatedBall = generator.generate();
        }
        return generatedBall;
    }
}
