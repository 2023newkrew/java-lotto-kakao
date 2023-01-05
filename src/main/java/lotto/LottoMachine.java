package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {
    private LottoWinningNumber lottoWinningNumber = null;

    public List<LottoTicket> purchaseLottoTickets(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);

        int ticketNumber = purchaseAmount / 1000;
        System.out.println(ticketNumber + "개를 구매했습니다.");

        List<LottoTicket> lottoTickets = new ArrayList<>();
        RandomLottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        for (int i = 0; i < ticketNumber; i++) {
            lottoTickets.add(lottoTicketGenerator.generate());
        }
        return lottoTickets;
    }

    public void setWinningNumber(List<Integer> winningNumbers, int bonusNumber) {
        List<LottoBall> winningBalls = winningNumbers.stream()
                .map(LottoBall::new)
                .collect(Collectors.toList());

        lottoWinningNumber = new LottoWinningNumber(winningBalls, new LottoBall(bonusNumber));
    }

    public MatchResult match(List<LottoTicket> lottoTickets) {
        Map<Ranking, Long> rankingCount = lottoTickets.stream()
                .map(lottoTicket -> lottoWinningNumber.calculateRanking(lottoTicket))
                .collect(Collectors.groupingBy(r -> r, Collectors.counting()));
        return new MatchResult(rankingCount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("로또 가격은 1000원입니다. 1000의 배수를 입력해야 합니다.");
        }
    }
}
