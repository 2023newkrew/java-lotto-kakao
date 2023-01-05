package lotto.model;

public enum Grade {
    ZERO(0),
    THREE(5000),
    FOUR(50000),
    FIVE(1500000),
    FIVE_BONUS(30000000),
    SIX(2000000000);

    final int reward;

    Grade(int reward) {
        this.reward = reward;
    }

    public static Grade getGrade(int count) {
        if (count == 3) return THREE;
        if (count == 4) return FOUR;
        if (count == 5) return FIVE;
        if (count == 15) return FIVE_BONUS;
        if (count == 6) return SIX;
        return ZERO;
    }

    public int getReward() {
        return this.reward;
    }
}