package domain;

public class LottoNumber {
    private final int position;
    private final int number;

    public LottoNumber(int position, int number) {
        this.position = position;
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public MatchStatus match(LottoNumber that) {
        if(this.isSamePosition(that) && this.isSameNumber(that)){
            return MatchStatus.MATCH;
        }
        return MatchStatus.NON_MATCH;
    }

    public MatchStatus match(BonusNumber bonusNumber) {
        if(this.isSameNumber(bonusNumber)){
            return MatchStatus.MATCH;
        }
        return MatchStatus.NON_MATCH;
    }

    private boolean isSameNumber(BonusNumber bonusNumber) {
        return this.number == bonusNumber.getNumber();
    }

    private boolean isSameNumber(LottoNumber lottoNumber) {
        return this.number == lottoNumber.getNumber();
    }

    private boolean isSamePosition(LottoNumber lottoNumber){
        return this.position == lottoNumber.getPosition();
    }
}
