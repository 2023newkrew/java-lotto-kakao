package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {

    public LottoTicket generate(LottoBallGenerator generator) {
        List<LottoBall> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() != 6) {
            lottoNumbers.add(generateEachBall(lottoNumbers, generator));
        }
        return new LottoTicket(lottoNumbers);
    }

    private LottoBall generateEachBall(List<LottoBall> lottoNumbers, LottoBallGenerator generator) {
        LottoBall generatedBall = generator.generateBall();
        for (; lottoNumbers.contains(generatedBall); generatedBall = generator.generateBall()) {
        }
        return generatedBall;
    }

    @FunctionalInterface
    public interface LottoBallGenerator {
        LottoBall generateBall();
    }
}
