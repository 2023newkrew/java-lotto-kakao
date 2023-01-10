package lotto.model;

public class MatchLottoTicket {
    public static Result matchLotto(LottoTickets lts, LottoTicket wn) {
        Result result = new Result();
        Grade key;
        for (LottoTicket ticket : lts.getTicket()) {
            key = wn.matchValues(ticket);
            result.put(key, result.get(key) + 1);
        }
        return result;
    }
}
