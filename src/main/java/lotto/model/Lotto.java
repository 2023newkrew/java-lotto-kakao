package lotto.model;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class Lotto {
    protected final List<LottoBall> balls = new ArrayList<>();

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