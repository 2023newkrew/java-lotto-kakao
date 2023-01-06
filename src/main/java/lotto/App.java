package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPayment;
import lotto.util.ManualLottoGenerator;
import lotto.util.RandomLottoGenerator;
import lotto.domain.Statistics;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        LottoView lottoView = new LottoView();
        List<Lotto> lottos = new ArrayList<>();

        LottoPayment payment = new LottoPayment(lottoView.inputPurchase());

        int manual = lottoView.inputManualAmount();
        payment.buyLotto(manual);
        lottos.addAll(ManualLottoGenerator.generateLottos(lottoView.inputManualLottos(manual)));

        int auto = payment.getAmount();
        payment.buyLotto(auto);
        lottos.addAll(RandomLottoGenerator.generateLottos(auto));

        lottoView.printManualAmount(manual);
        lottoView.printAutoAmount(auto);
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
