package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinnerScore {
    private final List<Integer> winnerScore;

    public WinnerScore() {
        this.winnerScore = new ArrayList<>(Collections.nCopies(5, 0));
    }

    public void addScore(int index){
        Integer value = this.winnerScore.get(index);
        this.winnerScore.set(index, value + 1);
    }

    public List<Integer> getWinnerScore(){
        return this.winnerScore;
    }

}
