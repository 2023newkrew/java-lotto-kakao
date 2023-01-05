package lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoMachine {
    private final RandomLottoTicketGenerator randomLottoGenerator = new RandomLottoTicketGenerator();
    private LottoWinningNumber winningLotto = null;

    public List<LottoTicket> purchaseLottoTickets(int purchaseAmount, List<List<Integer>> manualLottoNumbers) {
        validatePurchaseAmount(purchaseAmount);
        int numberOfLotto = purchaseAmount / 1000;

        Stream<LottoTicket> manualLottoStream = generateManualLottoStream(manualLottoNumbers);
        Stream<LottoTicket> randomLottoStream = generateRandomLottoStream(numberOfLotto - manualLottoNumbers.size());

        return Stream.concat(manualLottoStream, randomLottoStream)
                .collect(Collectors.toUnmodifiableList());
    }

    public void setWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        winningLotto = new LottoWinningNumber(convertToLottoBalls(winningNumbers), new LottoBall(bonusNumber));
    }

    public MatchResult match(List<LottoTicket> lottoTickets) {
        Map<Ranking, Long> rankingCount = lottoTickets.stream()
                .collect(Collectors.groupingBy(winningLotto::calculateRanking, Collectors.counting()));
        return new MatchResult(rankingCount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("로또 가격은 1000원입니다. 1000의 배수를 입력해야 합니다.");
        }
    }

    private Stream<LottoTicket> generateManualLottoStream(List<List<Integer>> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(this::convertToLottoBalls)
                .map(LottoTicket::new);
    }

    private Stream<LottoTicket> generateRandomLottoStream(int numberOfRandomLotto) {
        return IntStream.range(0, numberOfRandomLotto)
                .mapToObj(__ -> randomLottoGenerator.generate());
    }

    private List<LottoBall> convertToLottoBalls(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoBall::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
