package lotto.model;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class LottoTrial {
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT - 1; i++){
            result.append(balls.get(i)).append(", ");
        }

        result.append(balls.get(LottoConstants.BALLCOUNT_LIMIT - 1)).append("]");

        return result.toString();
    }
}