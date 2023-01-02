package lotto;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
