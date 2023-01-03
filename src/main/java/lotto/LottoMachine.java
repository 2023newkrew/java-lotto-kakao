package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {
    private LottoWinningNumber lottoWinningNumber = null;

    public List<LottoTicket> purchaseLottoTickets(int purchaseAmount) {
        int ticketNumber = purchaseAmount / 1000;
        System.out.println(ticketNumber + "개를 구매했습니다.");

        List<LottoTicket> lottoTickets = new ArrayList<>();
        RandomLottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        for (int i = 0; i < ticketNumber; i++) {
            lottoTickets.add(lottoTicketGenerator.generate());
        }
        return lottoTickets;
    }

    public void setWinningNumber(String winningNumberString, int bonusNumber) {
        List<LottoBall> winningNumbers = Arrays.stream(winningNumberString.split(","))
                .map(numberString -> Integer.parseInt(numberString))
                .map(number -> new LottoBall(number))
                .collect(Collectors.toList());

        lottoWinningNumber = new LottoWinningNumber(winningNumbers, new LottoBall(bonusNumber));
    }

    public MatchResult match(List<LottoTicket> lottoTickets) {
        Map<Ranking, Long> rankingCount = lottoTickets.stream()
                .map(lottoTicket -> lottoWinningNumber.calculateRanking(lottoTicket))
                .collect(Collectors.groupingBy(r -> r, Collectors.counting()));
        return new MatchResult(rankingCount);
    }
}
