package view;

import domain.Lotto;
import domain.LottoRank;
import dto.LottoResult;
import dto.PurchasedLotto;

import java.util.List;
import java.util.stream.Stream;

public class OutputView {

    public void printPurchasedLottos(PurchasedLotto purchased) {
        System.out.print("수동으로 " + purchased.getManual().size() + "개, ");
        System.out.print("자동으로 " + purchased.getAuto().size() + "개");
        System.out.println("를 구매했습니다.");
        Stream.concat(purchased.getManual().stream(), purchased.getAuto().stream())
                .forEach((System.out::println));
    }

    public void printLottoStatistics(LottoResult lottoResult) {
        for (LottoRank place : LottoRank.values()) {
            System.out.print(place);
            System.out.print(" - ");
            System.out.print(lottoResult.getLottoRanks().get(place));
            System.out.println("개");
        }
        System.out.print("총 수익률은 " + String.format("%.2f", lottoResult.getEarningRate()) + "%입니다.");
    }
}
