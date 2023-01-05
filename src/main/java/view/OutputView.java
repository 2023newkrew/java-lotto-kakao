package view;

import domain.Lotto;
import domain.Rank;
import dto.LottoResult;

import java.util.List;

public class OutputView {

    public static void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for(Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public static void printLottoStatistics(LottoResult lottoResult) {
        for (Rank place : Rank.values()) {
            System.out.println(String.format("%s- %d개", place.toString(), lottoResult.getLottoRanks().get(place)));
        }
        String result = lottoResult.getEarningRate() >= 1.0d ? "이익이" : "손해";
        System.out.print(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)", lottoResult.getEarningRate(), result));
    }
}
