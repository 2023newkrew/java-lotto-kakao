package lotto.dto;

import java.util.Arrays;
import lotto.domain.LottoPrize;
import lotto.domain.PrizeGroupingMap;

public class ResultDTO {

    private final PrizeGroupingMap prizeGroupingMap;

    private ResultDTO(PrizeGroupingMap prizeGroupingMap) {
        this.prizeGroupingMap = prizeGroupingMap;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        Arrays.stream(LottoPrize.values())
                .forEachOrdered((e) ->
                        message.append(String.format("%s (%d원) - %d개\n",
                                e.getDescription(), e.getPrizeMoney(),
                                prizeGroupingMap.getOrDefault(e, 0L))
                        ));
        message.append(String.format("총 수익률은 %.2f입니다.\n", prizeGroupingMap.getProfit()));

        return message.toString();
    }

    public static ResultDTO from(PrizeGroupingMap prizeGroupingMap) {
        return new ResultDTO(prizeGroupingMap);
    }
}
