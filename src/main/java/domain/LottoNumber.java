package domain;

public class LottoNumber {
    private final int location;
    private final int number;

    public LottoNumber(int number) {
        this(-1, number);
    }

    public LottoNumber(int location, int number) {
        this.location = location;
        this.number = number;
    }

    public MatchStatus match(LottoNumber other) {
        if(this.location == other.location && this.number == other.number){
            return MatchStatus.MATCH;
        }
        return MatchStatus.NON_MATCH;
    }
}
