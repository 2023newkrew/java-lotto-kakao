package lotto.model;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public class Lotto {
    private final List<LottoBall> balls = new ArrayList<>();

    public Lotto(Collection<LottoBall> balls) {
        this.balls.addAll(balls);

        check();
        sort();
    }

    public Lotto(List<Integer> balls) {
        for (int ball : balls) {
            this.balls.add(new LottoBall(ball));
        }

        check();
        sort();
    }

    void check() {
        Set<LottoBall> lottoSet = new HashSet<>(this.balls);

        if (lottoSet.size() != LottoConstants.BALLCOUNT_LIMIT) {
            throw new DuplicatedBallNumber();
        }
    }

    void sort() {
        Collections.sort(this.balls);
    }

    public List<LottoBall> getBalls(){
        return balls;
    }

    public LottoBall getBall(int index) { return balls.get(index); }
}