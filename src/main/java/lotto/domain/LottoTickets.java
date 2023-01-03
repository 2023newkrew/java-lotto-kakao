package lotto.domain;

import java.util.ArrayList;

public class LottoTickets {
    private final ArrayList<LottoTicket> tickets;
    private final LottoRandom lottoRandom;

    public LottoTickets(int amount) {
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }

        int count = amount / 1000;
        this.tickets = new ArrayList<>(count);
        this.lottoRandom = new LottoRandom();
        for (int i=0; i<count; i++){  // stream 으로 변경 필요
            tickets.add(new LottoTicket(lottoRandom.createRandomNumbers()));
        }
    }

    // 사용자가 입력한 금액이 1000원 이상인지 확인
    private boolean checkAmountUpperThan1000(int amount){
        return amount >= 1000;
    }

    public int getLottoTicketCount(){
        return tickets.size();
    }
}
