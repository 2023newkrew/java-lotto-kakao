package lotto.model;

public enum Grade {
    ZERO,
    THREE,
    FOUR,
    FIVE,
    FIVE_BONUS,
    SIX;

    public static Grade getGrade(int count) {
        if (count == 3) return THREE;
        if (count == 4) return FOUR;
        if (count == 5) return FIVE;
        if (count == 6) return SIX;
        if (count == 15) return FIVE_BONUS;
        return ZERO;
    }
}