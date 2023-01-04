package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.util.LottoPayment;
import lotto.util.RandomLottoGenerator;
import lotto.domain.Statistics;
import lotto.view.LottoView;

import java.util.List;

public class App {
    public static void main(String[] args) {
        LottoView lottoView = new LottoView();
        RandomLottoGenerator generator = new RandomLottoGenerator();
        int amount = LottoPayment.getAmount(lottoView.inputPurchase());
        List<Lotto> lottos = generator.generateLottos(amount);

        lottoView.printAmount(amount);
        lottoView.printLottos(lottos);

        Lotto winLotto = lottoView.inputWinNumbers();
        LottoNumber bonusNumber = lottoView.inputBonusNumber();

        Statistics stat = new Statistics();
        stat.build(lottos, winLotto, bonusNumber);

        lottoView.printStatisticsText();
        lottoView.printStatistics(stat);
        lottoView.printTotalProfitRate(stat);
    }
}
