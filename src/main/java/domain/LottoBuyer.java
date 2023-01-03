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

    public LottoBuyer(Integer money, LottoObtainPlace place) {
        this.money = money;
        this.lottos = place.buy(money);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoPlace> statuses = lottos.stream()
                .map(lotto -> lotto.getPlace(winningLotto))
                .collect(Collectors.toList());

        Map<LottoPlace, Integer> map = new EnumMap<>(LottoPlace.class);
        for (LottoPlace lottoPlace : LottoPlace.values()) {
            map.put(lottoPlace, Collections.frequency(statuses, lottoPlace));
        }

        Double earningRate =  calculateEarningRate(map);

        return new LottoResult(map, earningRate);
    }

    public Double calculateEarningRate(Map<LottoPlace, Integer> map) {
        Integer investMoney = (money / 1000) * 1000;
        Long earningMoney = 0L;
        for (LottoPlace lottoPlace : LottoPlace.values()) {
            earningMoney +=  (map.get(lottoPlace) * lottoPlace.getReward());
        }
        if (earningMoney == 0L) {
            return 0.00d;
        }
        return Math.floor(earningMoney / (double) investMoney * 100) / 100;
    }

}
