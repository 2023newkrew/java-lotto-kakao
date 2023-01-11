package lotto.model;

public class MatchLottoTicket {
    public static Result matchLotto(LottoTickets lts, LottoTicket wn) {
        Result result = new Result();
        Grade key;
        for (LottoTicket ticket : lts.getTicket()) {
            key = matchValues(ticket, wn);
            result.put(key, result.get(key) + 1);
        }
        return result;
    }

    public static Grade matchValues(LottoTicket t1, LottoTicket t2) {
        if (t2.getBonusNumber() == null) {
            LottoTicket temp = t1;
            t1 = t2;
            t2 = temp;
        }
        int sixCount = (int) t2.getLottoValues().stream().filter(t1::contains).count();
        int bonusCount = 0;
        if (sixCount == 5 && t1.contains(t2.getBonusNumber())) {
            bonusCount++;
        }
        return Grade.getGrade(sixCount + 10 * bonusCount);
    }
}
