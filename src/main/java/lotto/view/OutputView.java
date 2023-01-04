package lotto.view;

import static lotto.domain.LottoConstants.ZERO_WINNING;
import static lotto.view.Messages.BONUS_BALL_STRING;
import static lotto.view.Messages.MATCH_COUNT_STRING_FORMAT;
import static lotto.view.Messages.PRINT_GAME_RESULT_MESSAGE;
import static lotto.view.Messages.PRINT_LOTTO_COUNT_MESSAGE_FORMAT;
import static lotto.view.Messages.PRINT_RANK_REMAINING_MESSAGE_FORMAT;
import static lotto.view.Messages.PRINT_YIELD_MESSAGE_FORMAT;
import static lotto.view.Messages.READ_BONUS_BALL_MESSAGE;
import static lotto.view.Messages.READ_LOTTO_ANSWER_NUMBERS_MESSAGE;
import static lotto.view.Messages.READ_PRICE_MESSAGE;

import java.util.Arrays;
import java.util.List;
import lotto.domain.GameResultDto;
import lotto.domain.LottoHandler;
import lotto.domain.LottoRank;

public class OutputView {
    public void printReadPrice() {
        System.out.println(READ_PRICE_MESSAGE);
    }

    public void printCount(int lottoCount) {
        System.out.printf(PRINT_LOTTO_COUNT_MESSAGE_FORMAT, lottoCount);
    }

    public void printAllLotto(LottoHandler lottoHandler) {
        System.out.println(lottoHandler.toString());
    }

    public void printReadLottoAnswerNumbers() {
        System.out.println(READ_LOTTO_ANSWER_NUMBERS_MESSAGE);
    }

    public void printReadBonusBall() {
        System.out.println(READ_BONUS_BALL_MESSAGE);
    }

    public void printGameResult(GameResultDto gameResultDto) {
        List<Integer> rankCount = gameResultDto.getRankCount();
        System.out.println(PRINT_GAME_RESULT_MESSAGE);
        Arrays.stream(LottoRank.values())
                .sorted((rank1, rank2) -> Integer.compare(rank2.index(), rank1.index()))
                .filter(rank -> rank.winning() > ZERO_WINNING)
                .forEach(rank -> printSingleRankResult(rank, rankCount.get(rank.index())));
        System.out.printf(PRINT_YIELD_MESSAGE_FORMAT, gameResultDto.getYield());
    }

    private void printSingleRankResult(LottoRank rank, int count) {
        System.out.printf(MATCH_COUNT_STRING_FORMAT, rank.minMatchCount());
        if (rank.isRequiresBonus()) {
            System.out.print(BONUS_BALL_STRING);
        }
        System.out.printf(PRINT_RANK_REMAINING_MESSAGE_FORMAT, rank.winning(), count);
    }
}
