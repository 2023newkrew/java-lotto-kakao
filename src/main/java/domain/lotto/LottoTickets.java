package domain.lotto;

import java.util.*;

public class LottoTickets {
    private final List<LottoNumbers> autoTickets;
    private final List<LottoNumbers> manualTickets;

    public LottoTickets(List<LottoNumbers> autoTickets) {
        this(autoTickets, Collections.emptyList());
    }

    public LottoTickets(List<LottoNumbers> autoTickets, List<LottoNumbers> manualTickets) {
        validateTickets(autoTickets, manualTickets);
        this.autoTickets = new ArrayList<>(autoTickets);
        this.manualTickets = new ArrayList<>(manualTickets);
    }

    public List<LottoNumbers> getAllTickets() {
        List<LottoNumbers> allTickets = new ArrayList<>(autoTickets.size() + manualTickets.size());
        allTickets.addAll(autoTickets);
        allTickets.addAll(manualTickets);
        return allTickets;
    }

    public Cost calculatePurchaseCost() {
        return new Cost(countAllTickets() * LottoShop.LOTTO_PRICE);
    }

    public LottoPrizeResult matchTickets(LottoWinningNumbers lottoWinningNumbers) {
        Map<LottoPrize, Integer> prizeCounts = new EnumMap<>(LottoPrize.class);
        Arrays.stream(LottoPrize.values())
                .forEach(prize -> prizeCounts.put(prize, 0));

        getAllTickets().stream()
                .map(lottoNumbers -> LottoPrize.matchLottoNumbers(lottoNumbers, lottoWinningNumbers))
                .forEach(lottoPrize -> prizeCounts.computeIfPresent(lottoPrize, (prize, count) -> count + 1));

        return new LottoPrizeResult(prizeCounts);
    }

    public int countAllTickets() {
        return countAutoTickets() + countManualTickets();
    }

    public int countAutoTickets() {
        return autoTickets.size();
    }

    public int countManualTickets() {
        return manualTickets.size();
    }

    private void validateTickets(List<LottoNumbers> autoTickets, List<LottoNumbers> manualTickets) {
        if (autoTickets.isEmpty() && manualTickets.isEmpty()) {
            throw new IllegalArgumentException("로또 복권은 한 장 이상이어야 합니다.");
        }
    }
}
