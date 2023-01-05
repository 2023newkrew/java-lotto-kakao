package lotto.model.company;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.SingleLottoNumber;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoCompany {

    private final LottoNumber winningNumber;

    private final SingleLottoNumber bonus;

    public static LottoCompany create(LottoNumber winningNumber, SingleLottoNumber bonus) {
        if (Objects.isNull(winningNumber)) {
            throw new IllegalArgumentException("당첨 번호가 없습니다.");
        }
        if (Objects.isNull(bonus)) {
            throw new IllegalArgumentException("추가 번호가 없습니다.");
        }
        if (winningNumber.hasNumber(bonus)) {
            throw new IllegalArgumentException("중복된 당첨 번호는 생성할 수 없습니다.");
        }

        return new LottoCompany(winningNumber, bonus);
    }

    private LottoCompany(LottoNumber winningNumber, SingleLottoNumber bonus) {
        this.winningNumber = winningNumber;
        this.bonus = bonus;
    }

    public LottoStats analyze(LottoTicket ticket) {
        List<LottoRanking> rankings = ticket.stream()
                .map(this::judge)
                .collect(Collectors.toList());

        return LottoStats.from(rankings);
    }

    private LottoRanking judge(LottoNumber lotto){
        int commonNumberCount = winningNumber.countCommonNumber(lotto);
        boolean hasBonus = lotto.hasNumber(bonus);

        return LottoRanking.from(commonNumberCount, hasBonus);
    }

    @Override
    public String toString() {
        return "{" +
                "winningNumber=" + winningNumber +
                ", bonus=" + bonus +
                '}';
    }
}
