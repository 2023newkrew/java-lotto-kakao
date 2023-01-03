package domain;

import dto.LottoResult;
import dto.WinningLotto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoBuyer {
    private Integer money;
    private List<Lotto> lottos;

    public LottoBuyer(Integer money, LottoObtainPlace means) {
        this.money = money;
        this.lottos = means.obtain(money);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> statuses = lottos.stream()
                .map(lotto -> lotto.getRank(winningLotto))
                .collect(Collectors.toList());

        Map<LottoRank, Integer> map = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            map.put(lottoRank, Collections.frequency(statuses, lottoRank));
        }

        Double earningRate =  calculateEarningRate(map);

        return new LottoResult(map, earningRate);
    }

    public Double calculateEarningRate(Map<LottoRank, Integer> map) {
        Integer investMoney = (money / 1000) * 1000;
        Long earningMoney = 0L;
        for (LottoRank lottoRank : LottoRank.values()) {
            earningMoney +=  (map.get(lottoRank) * lottoRank.getReward());
        }
        if (earningMoney == 0L) {
            return 0.00d;
        }
        return Math.floor(earningMoney / (double) investMoney * 100) / 100;
    }

}
