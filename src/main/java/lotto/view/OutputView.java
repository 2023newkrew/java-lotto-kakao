package lotto.view;

import lotto.domain.GameResultDto;
import lotto.domain.LottoHandler;

public class OutputView {
    public void printReadPrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printCount(int lottoCount) {
        System.out.printf("%d개를 구매했습니다.\n", lottoCount);
    }

    public void printAllLotto(LottoHandler lottoHandler) {
        System.out.println(lottoHandler.toString());
    }

    public void printReadLottoAnswerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printReadBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printGameResult(GameResultDto gameResultDto) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println(gameResultDto.getResult());
        System.out.printf("총 수익률은 %.2f입니다.", gameResultDto.getYield());
    }
}
