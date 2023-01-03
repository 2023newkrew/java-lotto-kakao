package domain;

@FunctionalInterface
public interface WinnerCompareRule {
    LottoResult compare(LottoTicket lottoTicket);
}
