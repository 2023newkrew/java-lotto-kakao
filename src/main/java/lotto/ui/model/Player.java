package lotto.ui.model;

import lotto.core.LottoTicket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {
    private final ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
    private long ownMoney = 0;

    public long getOwnMoney() {
        return ownMoney;
    }

    public List<LottoTicket> getLottoTickets() {
        return List.copyOf(lottoTickets);
    }

    public void giveMoney(long money) {
        if (money < 0) {
            throw new IllegalArgumentException("음수인 돈을 줄 수 없습니다.");
        }
        this.ownMoney += money;
    }

    public void takeMoney(long money) {
        if (money < 0) {
            throw new IllegalArgumentException("음수인 돈을 받아갈 수 없습니다.");
        }
        if (money > ownMoney) {
            throw new RuntimeException("소지한 돈이 모자릅니다.");
        }
        this.ownMoney -= money;
    }

    public void giveTickets(Collection<LottoTicket> tickets) {
        lottoTickets.addAll(tickets);
    }
}
