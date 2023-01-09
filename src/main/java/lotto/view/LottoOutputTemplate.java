package lotto.view;


import java.util.stream.Collectors;
import lotto.model.LottoCount;
import lotto.model.LottoRank;

import static lotto.model.LottoRank.*;

import java.util.List;
import java.util.Map;
import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;

public class LottoOutputTemplate {
    public void printLottoTicket(LottoTicket lottoTicket) {
        System.out.println("[" + String.join(", ",
                lottoTicket.getLottoTicket()
                        .stream()
                        .map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
                        .collect(Collectors.toList()))
                + "]");
    }

    public void printLottoTickets(LottoCount lottoCount, LottoTickets lottoTickets) {
        Integer manualLottoCount = lottoCount.getManualLottoCount();
        Integer automaticLottoCount = lottoCount.getAutomaticLottoCount();
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + automaticLottoCount + "장을 구매했습니다.");
        List<LottoTicket> lottoTicketList = lottoTickets.getLottoTickets();
        lottoTicketList.forEach(this::printLottoTicket);
    }

    public void printLottoResult(LottoResult lottoResult) {
        Map<LottoRank, Integer> lottoResultRankCountMap = lottoResult.getRankCountMap();
        System.out.println("당첨 통계\n---------");
        System.out.println(
                RANK5.getMatchedCount().get(0) + "개 일치 (" + RANK5.getReward() + "원)- " + lottoResultRankCountMap.get(
                        RANK5) + "개");
        System.out.println(
                RANK4.getMatchedCount().get(0) + "개 일치 (" + RANK4.getReward() + "원)- " + lottoResultRankCountMap.get(
                        RANK4) + "개");
        System.out.println(
                RANK3.getMatchedCount().get(0) + "개 일치 (" + RANK3.getReward() + "원)- " + lottoResultRankCountMap.get(
                        RANK3) + "개");
        System.out.println(
                RANK2.getMatchedCount().get(0) + "개 일치, 보너스 볼 일치(" + RANK2.getReward() + "원)- "
                        + lottoResultRankCountMap.get(RANK2) + "개");
        System.out.println(
                RANK1.getMatchedCount().get(0) + "개 일치 (" + RANK1.getReward() + "원)- " + lottoResultRankCountMap.get(
                        RANK1) + "개");
    }

    public void printRateOfReturn(Double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.", rateOfReturn);
        System.out.println(rateOfReturnDescription(rateOfReturn));
    }

    private String rateOfReturnDescription(Double rateOfReturn) {
        if (rateOfReturn < 1) {
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        if (rateOfReturn > 1) {
            return "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        return "(기준이 1이기 때문에 결과적으로 이득도 손해도 아니라는 의미임)";
    }
}
