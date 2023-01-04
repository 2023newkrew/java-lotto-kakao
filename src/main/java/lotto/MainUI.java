package lotto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUI {
    private final Scanner scanner;
    private final PrintStream printer;
    private final ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
    private LottoWinningNumber lottoWinningNumber = null;
    private Price income = null;

    public MainUI(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printer = new PrintStream(outputStream);
    }

    public void initPurchasePrice() {
        printer.println("구입금액을 입력해 주세요.");
        int incomeValue = scanner.nextInt();
        if (incomeValue % 1000 != 0) {
            throw new RuntimeException("로또 가격은 1000원입니다. 1000의 배수를 입력해야 합니다.");
        }
        income = new Price(incomeValue);
        Stream.generate(LottoTicket::fromRandom)
                .limit(income.floorDivide(new Price(1000)))
                .collect(Collectors.toCollection(() -> lottoTickets));
    }

    public void initWinningNumber() {
        printer.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] lottoNumberStrings = scanner.next()
                .split(",");
        printer.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();

        lottoWinningNumber = new LottoWinningNumber(
                Arrays.stream(lottoNumberStrings)
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList())
                , new LottoBall(bonusNumber)
        );
    }

    public void printTickets() {
        for (LottoTicket lottoTicket : lottoTickets) {
            printer.println(lottoTicket);
        }
    }

    private Map<Ranking, Integer> calculateStatistics(List<LottoTicket> lottoTickets, LottoWinningNumber lottoWinningNumber) {
        Map<Ranking, Integer> rankingMap = new HashMap<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
            rankingMap.put(ranking, rankingMap.getOrDefault(ranking, 0) + 1);
        }
        return rankingMap;
    }

    public void run() {
        assertIsRunnableState();

        Map<Ranking, Integer> rankingMap = calculateStatistics(lottoTickets, lottoWinningNumber);
        Price outcome = Ranking.totalPrice(rankingMap);

        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.printf("3개 일치 (%s)- %d개\n", Ranking.FIFTH.intoPrice(), rankingMap.getOrDefault(Ranking.FIFTH, 0));
        System.out.printf("4개 일치 (%s)- %d개\n", Ranking.FOURTH.intoPrice(), rankingMap.getOrDefault(Ranking.FOURTH, 0));
        System.out.printf("5개 일치 (%s)- %d개\n", Ranking.THIRD.intoPrice(), rankingMap.getOrDefault(Ranking.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (%s)- %d개\n", Ranking.SECOND.intoPrice(), rankingMap.getOrDefault(Ranking.SECOND, 0));
        System.out.printf("6개 일치 (%s)- %d개\n", Ranking.FIRST.intoPrice(), rankingMap.getOrDefault(Ranking.FIRST, 0));
        System.out.printf("총 수익률은 %.6f 입니다.\n",
                outcome.ratio(income)
                        .doubleValue()
        );
    }

    /**
     * 실행 가능한 상태인지 확인해주는 메서드입니다.
     */
    private void assertIsRunnableState() {
        if (Objects.isNull(income)) {
            throw new RuntimeException("로또를 구매하지 않았습니다.");
        }
        if (Objects.isNull(lottoWinningNumber)) {
            throw new RuntimeException("로또 번호를 아직 입력받지 않았습니다.");
        }
    }
}
