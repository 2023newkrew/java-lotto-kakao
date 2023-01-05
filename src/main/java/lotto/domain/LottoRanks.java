package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoRanks {

    private final Map<LottoRank, Integer> lottoRanks;

    public static LottoRanks createLottoRanks(Map<LottoRank, Integer> lottoRanks) {
        return new LottoRanks(lottoRanks);
    }

    private LottoRanks(Map<LottoRank, Integer> lottoRanks) {
        this.lottoRanks = lottoRanks;
    }

    public Integer get(LottoRank rank) {
        return lottoRanks.get(rank);
    }

    public String getResultString() {
        List<LottoRank> entry = new java.util.ArrayList<>(List.of(LottoRank.values()));
        Double returnRate = calculateReturnRate();
        entry.remove(LottoRank.NOTHING);
        Collections.reverse(entry);
        Double rate = calculateReturnRate();
        String ranksString = entry.stream().map(rank -> LottoRank.getLottoRankString(rank, lottoRanks.get(rank)))
                .collect(Collectors.joining("\n"));
        return ranksString + String.format("\n%s", LottoConstants.STATICS_FORMAT(rate));
    }

    private Double calculateReturnRate() {

        int totalCount = Arrays.stream(LottoRank.values())
                .mapToInt(lottoRanks::get).sum();
        long totalAmount = Arrays.stream(LottoRank.values())
                .mapToLong(rank -> rank.getWinning() * lottoRanks.get(rank)).sum();
        return (double) totalAmount / ((long) totalCount * LottoConstants.LOTTO_PRICE);

    }


}
