import java.util.*;
import java.util.stream.Collectors;

public class LottoBuyer {
    private Integer money;
    private List<Lotto> lottos;
    private LottoResult lottoResult;

    LottoBuyer(Integer money, LottoObtainPlace place) {
        this.money = money;
        this.lottos = place.buy(money);
    }

    public LottoResult check(Lotto winningNumbers, LottoNumber bonusNumber) {
        List<LottoStatus> statuses = lottos.stream()
                .map(lotto -> lotto.getPlace(winningNumbers, bonusNumber))
                .collect(Collectors.toList());

        Map<LottoStatus, Integer> map = new EnumMap<>(LottoStatus.class);
        for (LottoStatus lottoStatus : LottoStatus.values()) {
            map.put(lottoStatus, Collections.frequency(statuses, lottoStatus));
        }
        this.lottoResult = new LottoResult(map);
        return this.lottoResult;
    }

    public Double calculateEarningRate() {
        Integer investMoney = (money / 1000) * 1000;
        Long earningMoney = 0L;
        for (LottoStatus lottoStatus : LottoStatus.values()) {
            earningMoney +=  (lottoResult.get(lottoStatus) * lottoStatus.getReward());
        }
        if (earningMoney == 0L) {
            return 0.00d;
        }
        return Math.floor(earningMoney / (double) investMoney * 100) / 100;
    }

}
