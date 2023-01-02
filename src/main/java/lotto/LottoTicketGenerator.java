package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {
    private final LottoBallGenerator generator;

    public LottoTicketGenerator(LottoBallGenerator generator) {
        this.generator = generator;
    }

    public LottoTicket generate() {
        List<LottoBall> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() != 6) {
            lottoNumbers.add(generateEachBall(lottoNumbers, generator));
        }
        return new LottoTicket(lottoNumbers);
    }

    private LottoBall generateEachBall(List<LottoBall> lottoNumbers, LottoBallGenerator generator) {
        LottoBall generatedBall = generator.generateBall();
        while (lottoNumbers.contains(generatedBall)) {
            generatedBall = generator.generateBall();
        }
        return generatedBall;
    }

    @FunctionalInterface
    public interface LottoBallGenerator {
        LottoBall generateBall();
    }
}
