package lotto.model.prize;

public class PrizeJudge {
    public static Prize getPrizeOf(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return Prize.FIRST;
        if (matchCount == 5 && hasBonus) return Prize.SECOND;
        if (matchCount == 5) return Prize.THIRD;
        if (matchCount == 4) return Prize.FOURTH;
        if (matchCount == 3) return Prize.FIFTH;
        return Prize.LOSING;
    }
}
