package lotto.domain;

public class Customer {

    private final LottoTickets lottoTickets;
    private final int amount;

    public Customer(LottoTickets lottoTickets, int amount) {
        this.lottoTickets = lottoTickets;
        this.amount = amount;

        // 입력한 금액이 1000원 미만이면 로또 구입 불가능
        if(!checkAmountUpperThan1000(amount)){
            throw new IllegalArgumentException("입력한 금액이 1000원 미만입니다.");
        }
    }

    // 사용자가 입력한 금액이 1000원 이상인지 확인
    private boolean checkAmountUpperThan1000(int amount){
        return amount >= 1000;
    }

}
