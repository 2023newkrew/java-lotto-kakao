package lotto.domain;

import java.util.List;

public class GameResult {

    GameResultDto result;


    public GameResult(List<Integer> rankCounts, int lottoCount) {
        result = new GameResultDto(rankCounts, lottoCount);
    }


    public GameResultDto getResult() {
        return result;
    }


}
