package lotto.view;


import lotto.model.LottoRank;

import static lotto.model.LottoRank.*;

import java.util.List;
import java.util.Map;

public class LottoOutputTemplate {
    public void printPurchaseHistory(List<String> purchaseHistory) {
        System.out.println(purchaseHistory.size() + "개를 구매했습니다.");
        purchaseHistory.forEach(System.out::println);
    }

    public void printLottoResult(Map<LottoRank, Integer> lottoResult){
        System.out.println(RANK5.getMatchedCount() + "개 일치 (" + RANK5.getReward() + "원)- " + lottoResult.get(RANK5) + "개");
        System.out.println(RANK4.getMatchedCount() + "개 일치 (" + RANK4.getReward() + "원)- " + lottoResult.get(RANK4) + "개");
        System.out.println(RANK3.getMatchedCount() + "개 일치 (" + RANK3.getReward() + "원)- " + lottoResult.get(RANK3) + "개");
        System.out.println(RANK2.getMatchedCount() + "개 일치, 보너스 볼 일치 (" + RANK2.getReward() + "원)- " + lottoResult.get(RANK2) + "개");
        System.out.println(RANK1.getMatchedCount() + "개 일치 (" + RANK1.getReward() + "원)- " + lottoResult.get(RANK1) + "개");
    }

    public void printRateOfReturn(Double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.", rateOfReturn);
        System.out.println(rateOfReturnDescription(rateOfReturn));
    }

    private String rateOfReturnDescription(Double rateOfReturn){
        if(rateOfReturn < 1){
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }

        return "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    }
}
