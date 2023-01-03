package view;

import domain.Lotto;
import domain.LottoRank;
import dto.LottoResult;

import java.util.List;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for(Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void printLottoStatistics(LottoResult lottoResult) {
        for (LottoRank place : LottoRank.values()) {
            System.out.print(place.toString());
            System.out.print(" - ");
            System.out.print(lottoResult.getLottoRanks().get(place));
            System.out.println("개");
        }
        System.out.print("총 수익률은 " + lottoResult.getEarningRate() + "입니다.");
        System.out.print("(기준이 1이기 때문에 결과적으로 ");
        String result = lottoResult.getEarningRate() >= 1.0d ? "이익이" : "손해";
        System.out.print(result + "라는 의미임)");
    }
}
