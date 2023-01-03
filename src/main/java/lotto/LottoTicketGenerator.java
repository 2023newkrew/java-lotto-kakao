package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketGenerator {
    private final LottoBallGenerator generator;

    public LottoTicketGenerator() {
        this.generator = new ShuffleBallGenerator();
    }

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

    public static class ShuffleBallGenerator implements LottoBallGenerator {
        private final List<Integer> candidates = Stream.iterate(1, v -> v + 1)
                .limit(45)
                .collect(Collectors.toList());
        private int index = 0;

        @Override
        public LottoBall generateBall() {
            int currentIndex = index;
            this.index = (this.index + 1) % 6;
            if (currentIndex % 6 == 0) {
                Collections.shuffle(candidates);
            }
            return new LottoBall(candidates.get(currentIndex));
        }
    }
}
