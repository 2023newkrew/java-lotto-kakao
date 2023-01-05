package view;

import dto.LottoResultDto;
import dto.LottoTicketDto;
import model.constant.LottoPlace;

import java.util.Map;

import static model.constant.LottoPlace.*;

public class View {

    public void printPriceMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printWinnerNumberMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void outputLottoTicket(LottoTicketDto lottoTicketDto) {
        lottoTicketDto.getLottoTicket().forEach(lotto -> System.out.println(lotto.toString()));

    }

    public void outputResultMessage(LottoResultDto lottoResultDto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        outputResult(lottoResultDto.getPlaceResult());
        System.out.print(String.format("총 수익률은 %.2f 입니다.", lottoResultDto.getRateResult()));
    }

    private void outputResult(Map<LottoPlace, Integer> placeResult) {
        System.out.println("3개 일치 (5000원)- " + placeResult.getOrDefault(FIFTH_PLACE, 0) + "개");
        System.out.println("4개 일치 (50000원)- " + placeResult.getOrDefault(FOURTH_PLACE, 0) + "개");
        System.out.println("5개 일치 (1500000원)- " + placeResult.getOrDefault(THIRD_PLACE, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + placeResult.getOrDefault(SECOND_PLACE, 0) + "개");
        System.out.println("6개 일치 (2000000000원)- " + placeResult.getOrDefault(FIRST_PLACE, 0) + "개");
    }
}
