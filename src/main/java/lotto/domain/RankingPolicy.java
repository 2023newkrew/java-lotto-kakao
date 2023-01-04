package lotto.domain;

public interface RankingPolicy {
    LottoRank grade(LottoResult lottoResult);
}
