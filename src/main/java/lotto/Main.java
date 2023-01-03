package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Cash cash;
    public static LottoCount lottoCount;
    public static LottoTrials lottoTrials = new LottoTrials();
    public static WinNumber winNumber;
    public static TotalResult totalResult = new TotalResult();

    public static void main(String[] args) {
        try {
            inputCash();
            createLotto(lottoCount);
            inputWinningLotto();
            processLotto();
        }
        catch(Exception e){
            System.out.println(e.getClass() + " : " + e.getMessage());
        }
    }

    private static void inputCash() {
        System.out.println("구입금액을 입력해 주세요.");

        cash = new Cash(Long.parseLong(sc.nextLine()));

        lottoCount = new LottoCount(cash);

        System.out.println(lottoCount + "를 구매했습니다.");
    }

    private static void createLotto(LottoCount lottoCount) {
        for (int i = 0; i < lottoCount.getCount(); i++){
            LottoTrial lottoTrial = new LottoTrialRandom(new LottoPickerRandom());

            System.out.println(lottoTrial);

            lottoTrials.add(lottoTrial);
        }
    }

    private static void inputWinningLotto() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");

        List<LottoBallNumber> winNum = Arrays.stream(sc.nextLine().split(","))
                .map(v -> new LottoBallNumber(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());

        System.out.println("보너스 볼을 입력해 주세요.");

        winNumber = new WinNumber(new LottoTrialManual(winNum), new LottoBallNumber(sc.nextInt()));

        System.out.println();
    }

    private static void processLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            totalResult.addResult(winNumber.compareLotto(lottoTrials.get(i)));
        }

        System.out.println(totalResult);
    }
}
