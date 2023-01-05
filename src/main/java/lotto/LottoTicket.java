package lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket {
    protected final Set<LottoBall> lottoBalls;

    public LottoTicket(Collection<LottoBall> lottoBalls) {
        Set<LottoBall> balls = Set.copyOf(lottoBalls);
        if (balls.size() != lottoBalls.size()) {
            throw new IllegalArgumentException("각 숫자는 중복을 허용하지 않습니다.");
        }
        if (balls.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        this.lottoBalls = balls;
    }

    public static LottoTicket fromRandom() {
        List<LottoBall> candidates = IntStream.rangeClosed(1, 45)
                .boxed()
                .map(LottoBall::new)
                .collect(Collectors.toList());
        Collections.shuffle(candidates);
        return new LottoTicket(candidates.subList(0, 6));
    }

    public static LottoTicket parse(String raw) {
        return new LottoTicket(
                Arrays.stream(raw.split(","))
                        .map(LottoBall::parse)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBalls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoBalls, that.lottoBalls);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]", lottoBalls.stream()
                        .sorted()
                        .map(LottoBall::toString)
                        .collect(Collectors.joining(", "))
        );
    }
}
