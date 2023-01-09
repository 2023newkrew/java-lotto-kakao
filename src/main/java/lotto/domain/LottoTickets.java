package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<LottoTicket> ticket;
    private final LottoRandom lottoRandom;
    private final int purchaseCount;

    public LottoTickets(int amount, List<LottoTicket> manual) {
        // 입력한 금액이 1000원 미만이면 로또 구입 불가능
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }

        this.purchaseCount = amount / 1000;
        this.lottoRandom = new LottoRandom();
        this.ticket = IntStream.range(0, purchaseCount)
                .mapToObj(i -> new LottoTicket(lottoRandom.createRandomNumbers()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(purchaseCount)));
        this.ticket.addAll(manual);
    }

    // 사용자가 입력한 금액이 1000원 이상인지 확인
    private boolean checkAmountUpperThan1000(int amount){
        return amount >= 1000;
    }

    public int getLottoTicketSize(){
        return ticket.size();
    }

    public List<LottoTicket> getTicket(){
        return Collections.unmodifiableList(this.ticket);
    }

    public int getPurchaseCount() {
        return this.purchaseCount;
    }
}
