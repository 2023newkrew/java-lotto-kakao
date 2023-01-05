package lotto.view;

import static lotto.LottoConfig.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

public class LottoView {
    private static final String REQUEST_RECEIVE_PURCHASE_INPUT = "구매금액을 입력해 주세요.";
    private static final String REQUEST_RECEIVE_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_RECEIVE_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String REQUEST_RECEIVE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_RECEIVE_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
    private final Scanner scanner;
    private int purchasePrice;
    private int manualLottoCount;


    public LottoView() {
         scanner = new Scanner(System.in);
    }

    public int receivePurchasePrice() {
        System.out.println(REQUEST_RECEIVE_PURCHASE_INPUT);
        purchasePrice = Integer.parseInt(scanner.nextLine());
        return purchasePrice;
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.printf(
                "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n",
                manualLottoCount,
                lottos.size() - manualLottoCount
        );
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    public WinningLotto receiveWinningLotto() {
        return new WinningLotto(receiveWinningLottoNumbers(), receiveBonusNumber());
    }

    private List<Integer> receiveWinningLottoNumbers() {
        System.out.println(REQUEST_RECEIVE_WINNING_LOTTO_NUMBERS);
        return Arrays.stream(scanner.nextLine()
                        .split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int receiveBonusNumber() {
        System.out.println(REQUEST_RECEIVE_BONUS_NUMBER);
        return scanner.nextInt();
    }

    public void printResult(GameResultDto gameResultDto) {
        System.out.println("당첨 통계");
        System.out.println("-----------");
        gameResultDto.getLottoResults()
                .forEach(System.out::println);
        System.out.printf("총 수익률은 %.2f\n", gameResultDto.getRateOfReturn());
    }

    public List<Lotto> receiveManualLottos() {
        receiveManualLottoCount();
        System.out.println(REQUEST_RECEIVE_MANUAL_LOTTO);
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> receiveManualLotto())
                .collect(Collectors.toList());
    }

    private void receiveManualLottoCount() {
        System.out.println(REQUEST_RECEIVE_MANUAL_LOTTO_COUNT);
        manualLottoCount = Integer.parseInt(scanner.nextLine());
        if (manualLottoCount * LOTTO_PRICE > purchasePrice) {
            throw new IllegalArgumentException(
                    String.format("구매할 수 있는 로또의 갯수는 %d개 입니다.", purchasePrice / LOTTO_PRICE)
            );
        }
    }

    private Lotto receiveManualLotto() {
        return new Lotto(
                Arrays.stream(
                        scanner.nextLine()
                                .split(", ")
                        )
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }
}
