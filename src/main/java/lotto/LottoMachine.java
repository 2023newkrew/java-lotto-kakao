package lotto;

import lotto.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoMachine {
    private final RandomLottoTicketGenerator randomLottoGenerator;
    private LottoWinningNumber winningLotto = null;

    public LottoMachine(RandomLottoTicketGenerator randomLottoGenerator) {
        this.randomLottoGenerator = randomLottoGenerator;
    }

    public List<LottoTicket> purchaseLottoTickets(int purchaseAmount, List<List<Integer>> manualLottoNumbers) {
        validatePurchaseAmount(purchaseAmount, manualLottoNumbers.size());
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

    private void validatePurchaseAmount(int purchaseAmount, int numberOfManualLotto) {
        if (purchaseAmount == 0) {
            throw new IllegalArgumentException("로또를 1장 이상 구매해야 합니다.");
        }
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("로또 가격은 1000원입니다. 구입 금액으로 1000의 배수를 입력해야 합니다.");
        }
        if (purchaseAmount / 1000 < numberOfManualLotto) {
            throw new IllegalArgumentException("구입 금액에 비해 많은 수량의 수동 로또를 구매할 수 없습니다.");
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
