package lotto.domain;

import static lotto.exception.ExceptionMessages.INCOMPLETE_RANK_EXCEPTION_MESSAGE;

import java.util.Arrays;

public class DefaultRankingPolicy implements RankingPolicy {
    @Override
    public LottoRank grade(LottoResult lottoResult) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.rankCritera().isSatisfiedBy(lottoResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INCOMPLETE_RANK_EXCEPTION_MESSAGE));
    }
}
