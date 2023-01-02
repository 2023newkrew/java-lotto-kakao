import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoBuyer {
    private Integer money;
    private List<Lotto> lottos;

    private LottoResult lottoResult;

    LottoBuyer(Integer money, LottoObtainPlace place) {
        this.money = money;
        this.lottos = place.buy(money);
    }

    public LottoResult check(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        List<LottoStatus> statuses = lottos.stream()
                .map(lotto -> lotto.getPlace(winningNumbers, bonusNumber))
                .collect(Collectors.toList());

        Map<LottoStatus, Integer> map = new HashMap<>();
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
        System.out.println(earningMoney);
        return Math.floor(earningMoney / (double) investMoney * 100) / 100;
    }

}
