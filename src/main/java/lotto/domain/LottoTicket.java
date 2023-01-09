package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        this.lottoBalls = new ArrayList<>(lottoBalls).stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());

        if (lottoBalls.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        if (hasDuplicate()) {
            throw new IllegalArgumentException("로또 숫자는 중복될 수 없습니다.");
        }
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
}
