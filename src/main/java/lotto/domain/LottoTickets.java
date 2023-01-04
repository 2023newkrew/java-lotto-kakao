package lotto.domain;

import java.util.ArrayList;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoTickets {
    private final ArrayList<LottoTicket> tickets;

    public LottoTickets(int amount) {
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }
        int count = amount / MIN_PURCHASE_PRICE;
        this.tickets = new ArrayList<>(count);
        LottoRandom lottoRandom = new LottoRandom();
        for (int i=0; i<count; i++){
            tickets.add(new LottoTicket(lottoRandom.createRandomNumbers()));
        }
    }

    public int getLottoTicketCount(){
        return tickets.size();
    }

    public ArrayList<LottoTicket> getTickets(){
        return this.tickets;
    }

    private boolean checkAmountUpperThan1000(int amount){
        return amount >= MIN_PURCHASE_PRICE;
    }
}
