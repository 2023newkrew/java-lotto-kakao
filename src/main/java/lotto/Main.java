package lotto;

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Price income = scanIncome();

        List<LottoTicket> lottoTickets = generateLottoTickets(income);
        printLottoTickets(lottoTickets);

        LottoWinningNumber lottoWinningNumber = scanWinningNumber();

        printStatistics(income, lottoTickets, lottoWinningNumber);
    }

    private static void printLottoTickets(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    private static List<LottoTicket> generateLottoTickets(Price income) {
        long ticketNumber = income.floorDivide(new Price(1000));
        System.out.println(ticketNumber + "개를 구매했습니다.");

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketNumber; i++) {
            LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator(() -> new LottoBall(new Random().nextInt(45) + 1));
            LottoTicket generatedTicket = lottoTicketGenerator.generate();
            lottoTickets.add(generatedTicket);
        }
        return lottoTickets;
    }

    private static LottoWinningNumber scanWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] lottoNumberStrings = sc.next()
                .split(",");

        List<LottoBall> lottoNumbers = new ArrayList<>();
        for (String lottoNumber : lottoNumberStrings) {
            lottoNumbers.add(new LottoBall(Integer.parseInt(lottoNumber)));
        }

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = sc.nextInt();

        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumbers, new LottoBall(bonusNumber));
        return lottoWinningNumber;
    }

    private static Map<Ranking, Integer> calculateStatistics(List<LottoTicket> lottoTickets, LottoWinningNumber lottoWinningNumber) {
        Map<Ranking, Integer> rankingMap = new HashMap<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            Ranking ranking = lottoWinningNumber.calculateRanking(lottoTicket);
            rankingMap.put(ranking, rankingMap.getOrDefault(ranking, 0) + 1);
        }
        return rankingMap;
    }

    private static Price scanIncome() {
        System.out.println("구입금액을 입력해 주세요.");
        int incomeValue = sc.nextInt();
        if (incomeValue % 1000 != 0) {
            throw new IllegalArgumentException("로또 가격은 1000원입니다. 1000의 배수를 입력해야 합니다.");
        }
        return new Price(incomeValue);
    }

    private static void printStatistics(Price income, List<LottoTicket> lottoTickets, LottoWinningNumber lottoWinningNumber) {
        Map<Ranking, Integer> rankingMap = calculateStatistics(lottoTickets, lottoWinningNumber);
        Price outcome = Ranking.totalPrice(rankingMap);

        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.printf("3개 일치 (5000원)- %d개\n", rankingMap.getOrDefault(Ranking.FIFTH, 0));
        System.out.printf("4개 일치 (50000원)- %d개\n", rankingMap.getOrDefault(Ranking.FOURTH, 0));
        System.out.printf("5개 일치 (1500000원)- %d개\n", rankingMap.getOrDefault(Ranking.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (30000000원)- %d개\n", rankingMap.getOrDefault(Ranking.SECOND, 0));
        System.out.printf("6개 일치 (2000000000원)- %d개\n", rankingMap.getOrDefault(Ranking.FIRST, 0));
        System.out.printf("총 수익률은 %f입니다.\n", income.scale(outcome));
    }
}
