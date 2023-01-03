package lotto.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final ArrayList<LottoTicket> tickets;
    private final LottoRandom lottoRandom;

    public LottoTickets(int amount) {
        // 입력한 금액이 1000원 미만이면 로또 구입 불가능
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }

        int count = amount / 1000;
        this.lottoRandom = new LottoRandom();
        this.tickets = IntStream.range(0, count)
                .mapToObj(i -> new LottoTicket(lottoRandom.createRandomNumbers()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(count)));
    }

    // 사용자가 입력한 금액이 1000원 이상인지 확인
    private boolean checkAmountUpperThan1000(int amount){
        return amount >= 1000;
    }

    public int getLottoTicketCount(){
        return tickets.size();
    }

    public ArrayList<LottoTicket> getTickets(){
        return this.tickets;
    }
}
