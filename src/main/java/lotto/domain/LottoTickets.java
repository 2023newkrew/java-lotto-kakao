package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(){
        this.tickets = new ArrayList<>();
    }

    public LottoTickets(int amount) {
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }
        this.tickets = new ArrayList<>(amount/MIN_PURCHASE_PRICE);
    }

    public void createManualTicket(LottoTicket ticket){
        this.tickets.add(ticket);
    }

    public void createRandomTickets(int count){
        for (int i=0; i<count; i++){
            this.tickets.add(new LottoTicket());
        }
    }

    public int getLottoTicketCount(){
        return tickets.size();
    }

    public List<LottoTicket> getTickets(){
        return this.tickets;
    }

    private boolean checkAmountUpperThan1000(int amount){
        return amount >= MIN_PURCHASE_PRICE;
    }
}
