package lotto;

import java.util.*;

public class LottoTicket {
    private final List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        this.lottoBalls = lottoBalls;

        if (lottoBalls.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        if (hasDuplicate()) {
            throw new IllegalArgumentException("로또 숫자는 중복될 수 없습니다.");
        }

        Collections.sort(lottoBalls);
    }

    public int countMatchingNumber(LottoTicket targetLotto) {
        return (int) targetLotto.lottoBalls
                .stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoBall lottoBall) {
        return lottoBalls.contains(lottoBall);
    }

    private boolean hasDuplicate() {
        Set<LottoBall> set = new HashSet<>(lottoBalls);
        return set.size() != lottoBalls.size();
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
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
}
